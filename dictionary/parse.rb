require "pp"

$a = Dir.entries(".")

def getUniqueWordList
	words = []
	2.upto($a.length-1) do |i|
		if ($a[i] =~ /indicators\.txt/)
			temp = []
			File.readlines($a[i]).each do |line|
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
end

def addTagsToWords words
	2.upto($a.length-1) do |i|
		if ($a[i] =~ /indicators\.txt/)
			temp = []
	 		File.readlines("#{$a[i]}").each do |line|
				temp.push(line.strip)
			end
			tag = temp.slice!(0)
			$tags.push tag
			temp.each do |reading|
				word = words.select {|s| s[:word] == reading }[0]
				word[tag] = true
			end

	 	end  
	end
	return words
end

def addAbbrToWords words
	2.upto($a.length-1) do |i|
		if ($a[i] =~ /abbreviations_and_others\.txt/)
	 		File.readlines("#{$a[i]}").each do |line|
				vals = line.split("=")
				wrd = vals[0].strip
				abbr = vals[1].strip
				word = words.select {|s| s[:word] == wrd }[0]
				if (word)
					word["Abbr"] = abbr
				else
					words.push({:word => wrd, "Abbr" => abbr});
				end
			end
	 	end  
	end
	return words
end

uniqueWords = getUniqueWordList
$tags = []
uniqueWords = addTagsToWords uniqueWords 
uniqueWords = addAbbrToWords uniqueWords 

$sql = <<-eos
BEGIN TRANSACTION;

DROP TABLE IF EXISTS 'android_metadata';
CREATE TABLE 'android_metadata' ('locale' TEXT DEFAULT 'en_US');
INSERT INTO 'android_metadata' VALUES ('en_US');

DROP TABLE IF EXISTS 'Word';
CREATE TABLE 'Word' (
  '_id'	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
  'Word'	TEXT NOT NULL UNIQUE,
eos

def removeComma
	$sql = $sql[0...-1]
end

$tags.each do |tag|
	$sql += "  '#{tag}' INTEGER NOT NULL,\n"
end

$sql += "  'Abbr' TEXT);\n\n"

$sql += "INSERT INTO 'Word' ('Word', "

$tags.each do |tag|
	$sql += "'#{tag}', "
end

$sql += "'Abbr') \nVALUES\n"

uniqueWords.each do |word|
	$sql += "  (\"#{word[:word].upcase}\", "
	$tags.each do |tag|
		$sql += word[tag] ? '1' : '0'
		$sql += ", "
	end
	$sql += (word["Abbr"] ? "\"#{word["Abbr"]}\"" : "NULL")
	$sql += "),\n"
end
removeComma
removeComma
$sql += ";\n"


$sql += "COMMIT;"

puts $sql