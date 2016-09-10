require "pp"

a = Dir.entries(".")
f = {}

words = []
2.upto(a.length-1) do |i|
	if (a[i] =~ /indicators\.txt/)
		temp = []
 		File.readlines(a[i]).each do |line|
			temp.push(line.strip)
		end
		temp.slice!(0) 
		temp.each do |word|
			words.push({
				:word => word
				});
		end
 	end  
end

words.uniq!

tags = []
2.upto(a.length-1) do |i|
	if (a[i] =~ /indicators\.txt/)
		temp = []
 		File.readlines("#{a[i]}").each do |line|
			temp.push(line.strip)
		end
		tag = temp.slice!(0)
		tags.push tag
		temp.each do |reading|
			word = words.select {|s| s[:word] == reading }[0]
			word[tag] = true
		end

 	end  
end

$sql = <<-eos
		BEGIN TRANSACTION;

		CREATE TABLE 'android_metadata' ('locale' TEXT DEFAULT 'en_US');
		INSERT INTO 'android_metadata' VALUES ('en_US');

		CREATE TABLE 'Word' (
			'_id'	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
			'Word'	TEXT NOT NULL UNIQUE,
  eos

def removeComma
	$sql = $sql[0...-1]
end

tags.each do |tag|
	$sql += "'#{tag}' INTEGER NOT NULL,"
end

removeComma

$sql += <<-eos
		);
		INSERT INTO 'Word' ('Word', 
	eos

tags.each do |tag|
	$sql += "'#{tag}',"
end

removeComma
$sql += <<-eos
			) VALUES
			eos
words.each do |word|
	$sql += "(\"#{word[:word]}\", "
	tags.each do |tag|
		$sql += word[tag] ? '1' : '0'
		$sql += ","
	end
	removeComma
	$sql += "),"
end

removeComma

$sql += <<-eos 
;
			COMMIT;
	eos

puts $sql