require "pp"

$files_in_dir = Dir.entries(".")
$files_in_dir.delete_if {|i| i == "." || i == ".."}

def loadIndicatorWords
	words = []
	$files_in_dir.each do |file_name|
		if (file_name =~ /indicators\.txt/)
			lines = []
			File.readlines(file_name).each do |line|
				next if line == nil
				lines.push(line.strip)
			end

			indicator_type = lines.slice!(0)
			$indicator_types.push indicator_type

			lines.each do |line|
				line.upcase!
				word = words.select {|s| s[:word] == line }[0]
				if !word
					word = {
						:word => line
					}
					words.push(word);
				end
				word[indicator_type] = true
			end
		end
	end
	words.uniq!
	return words
end

def loadCharadeWords 
	words = []
	$files_in_dir.each do |file_name|
		if (file_name =~ /charades\.txt/)
	 		File.readlines(file_name).each do |line|
				next if line == nil
				vals = line.split("=")
				wrd = vals[0].strip.upcase
				charade = vals[1].strip.upcase
				word = words.select {|s| s[:word] == wrd }[0]
				if (word)
					if word[:charades].include? charade

					else
						word[:charades].push charade
					end
				else
					words.push({:word => wrd, :charades => [charade]});
				end
			end
	 	end  
	end
	return words
end


def mergeWordLists charades, indicators
	charades.each do |charade|
		already_exists = indicators.select {|s| s[:word] == charade[:word] }[0]
		if already_exists
			already_exists[:charades] = charade[:charades]
		else
			indicators.push charade
		end
	end
	return indicators
end

#
# Parse input
# 

$indicator_types = []
indicatorWords = loadIndicatorWords

puts "Indicators"
pp $indicator_types
pp indicatorWords
puts 

charadeWords = loadCharadeWords

puts "Charades"
pp charadeWords
puts

uniqueWords = mergeWordLists charadeWords, indicatorWords

puts
puts "Merged"
pp uniqueWords
puts


#
#  Construct output
#

def remove_last_2 sql
	sql = sql[0...-1]
	sql = sql[0...-1]
	return sql	
end

def getSqlPrep
	sql = <<-eos
	BEGIN TRANSACTION;

	DROP TABLE IF EXISTS 'android_metadata';
	CREATE TABLE 'android_metadata' ('locale' TEXT DEFAULT 'en_US');
	INSERT INTO 'android_metadata' VALUES ('en_US');

	DROP TABLE IF EXISTS 'Word';
	CREATE TABLE 'Word' (
	  Id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	  Word	TEXT NOT NULL UNIQUE,
	eos

	$indicator_types.each do |indicator_type|
		sql += "  '#{indicator_type}' INTEGER NOT NULL,\n"
	end

	sql = remove_last_2 sql

	sql += ");\n\n"

	sql += <<-eos
	DROP TABLE IF EXISTS 'Charade';
	CREATE TABLE 'Charade' (
	  Id 		INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	  Charade 	TEXT NOT NULL,
	  Word_Id 	INTEGER NOT NULL,

	  UNIQUE (Charade, Word_Id),
	  FOREIGN KEY(Word_id) REFERENCES Word(Id));

	 eos

	return sql
end


def addInsertions sql, words
	sql += "INSERT INTO 'Word' ('Word', "

	$indicator_types.each do |indicator_type|
		sql += "'#{indicator_type}', "
	end

	sql = remove_last_2 sql

	sql += ") \nVALUES\n"

	words.each do |word|
		sql += "  (\"#{word[:word]}\", "
		$indicator_types.each do |indicator_type|
			sql += word[indicator_type] ? '1' : '0'
			sql += ", "
		end
		sql = remove_last_2 sql

		sql += "),\n"
	end

	sql = remove_last_2 sql

	sql += ";\n\n"
	return sql
end

sql = getSqlPrep

uniqueWords.each_slice(500) do |slice|
	sql = addInsertions sql, slice
end

uniqueWords.each do |word|
	if word[:charades]
		word[:charades].each do |charade|
			sql += "INSERT INTO Charade (Charade, Word_Id) \n"
			sql += "SELECT \"#{charade}\", Id FROM Word WHERE Word.Word = \"#{word[:word]}\";\n\n"
		end
	end
end

sql += "COMMIT;"

output = File.open( "output.txt","w" )
output << sql
output.close

puts sql