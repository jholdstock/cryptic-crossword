require "pp"

$files_in_dir = Dir.entries(".")
$files_in_dir.delete_if {|i| i == "." || i == ".."}

def getUniqueWordList
	words = []
	$files_in_dir.each do |file_name|
		if (file_name =~ /indicators\.txt/)
			temp = []
			File.readlines(file_name).each do |line|
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
	return words
end

def addTagsToWords words
	$files_in_dir.each do |file_name|
		if (file_name =~ /indicators\.txt/)
			temp = []
	 		File.readlines(file_name).each do |line|
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
	$files_in_dir.each do |file_name|
		if (file_name =~ /abbreviations_and_others\.txt/)
	 		File.readlines(file_name).each do |line|
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



#
# Parse input
# 

uniqueWords = getUniqueWordList
$tags = []
uniqueWords = addTagsToWords uniqueWords 
uniqueWords = addAbbrToWords uniqueWords 


#
#  Construct output
#

def getSqlPrep
	sql = <<-eos
	BEGIN TRANSACTION;

	DROP TABLE IF EXISTS 'android_metadata';
	CREATE TABLE 'android_metadata' ('locale' TEXT DEFAULT 'en_US');
	INSERT INTO 'android_metadata' VALUES ('en_US');

	DROP TABLE IF EXISTS 'Word';
	CREATE TABLE 'Word' (
	  '_id'	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	  'Word'	TEXT NOT NULL UNIQUE,
	eos

	$tags.each do |tag|
		sql += "  '#{tag}' INTEGER NOT NULL,\n"
	end

	sql += "  'Abbr' TEXT);\n\n"
	return sql
end


def addInsertions sql, words
	sql += "INSERT INTO 'Word' ('Word', "

	$tags.each do |tag|
		sql += "'#{tag}', "
	end

	sql += "'Abbr') \nVALUES\n"

	words.each do |word|
		sql += "  (\"#{word[:word].upcase}\", "
		$tags.each do |tag|
			sql += word[tag] ? '1' : '0'
			sql += ", "
		end
		sql += (word["Abbr"] ? "\"#{word["Abbr"]}\"" : "NULL")
		sql += "),\n"
	end

	sql = sql[0...-1]
	sql = sql[0...-1]

	sql += ";\n"
	return sql
end

sql = getSqlPrep

uniqueWords.each_slice(500) do |slice|
	sql = addInsertions sql, slice
end

sql += "COMMIT;"

output = File.open( "output.txt","w" )
output << sql
output.close

puts sql