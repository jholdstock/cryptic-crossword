require 'sinatra'

set :bind => '0.0.0.0',
	:port => 8090,
	:dump_errors => true,
	:logging => true

get "/crossword-solver/:term" do
	sleep 0.2
	term = params[:term]
	sleep 3 if term == "slow"
	return 404 if term == "error"
	%~<!DOCTYPE html>
<html lang="en">
<body>
<table>
<tbody class='wp-widget-content'>
<tr class=subtitle>

<td>RANK</td><td>ANSWER</td><td>&nbsp;CLUE</td></tr>


<tr><td><div class=stars><div></div><div></div><div></div><div></div><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/REPARTEE">REPARTEE</a></td><td class=clue>Wordplay</td></tr>


<tr><td><div class=stars><div></div><div></div><div></div><div></div><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/CONVERSATION">CONVERSATION</a></td><td class=clue>Wordplay</td></tr>


<tr><td><div class=stars><div></div><div></div><div></div><div></div><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/PUNS">PUNS</a></td><td class=clue>Wordplay</td></tr>


<tr><td><div class=stars><div></div><div></div><div></div><div></div><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/ANAGRAM">ANAGRAM</a></td><td class=clue>Wordplay</td></tr>


<tr><td><div class=stars><div></div><div></div><div></div><div></div><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/HUMOR">HUMOR</a></td><td class=clue>Wordplay</td></tr>


<tr><td><div class=stars><div></div><div></div><div></div><div></div><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/WIT">WIT</a></td><td class=clue>Wordplay</td></tr>


<tr><td><div class=stars><div></div><div></div><div></div><div></div><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/PUN">PUN</a></td><td class=clue>Wordplay</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/TOMSWIFTY">TOMSWIFTY</a></td><td class=clue>Bit of adverbial wordplay</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/SPOONERISM">SPOONERISM</a></td><td class=clue>Promises no unfair wordplay (10)</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/ACROSTIC">ACROSTIC</a></td><td class=clue>Wordplay of a Socratic kind</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/PUNNING">PUNNING</a></td><td class=clue>Engaging in wordplay (7)</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/JASONMRAZ">JASONMRAZ</a></td><td class=clue>"Wordplay" vocalist, 2005</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/PUNSTERS">PUNSTERS</a></td><td class=clue>Wordplay specialists</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/ELLEN">ELLEN</a></td><td class=clue>Ripstein of "Wordplay"</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/TROPICALPUN">TROPICALPUN</a></td><td class=clue>Wordplay in the rain forest?</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/TILES">TILES</a></td><td class=clue>Pieces of wordplay?</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/ANAG">ANAG</a></td><td class=clue>Pc. of wordplay</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/PULLIN">PULLIN</a></td><td class=clue>Wordplay comes across flipping poorly? Do this to make things tighter (4,2)</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/NUPTIAL">NUPTIAL</a></td><td class=clue>Wordplay upset tail-spin of wedding (7)</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/WITTY">WITTY</a></td><td class=clue>One Direction ditty doesn't start with clever wordplay (5)</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/OWNUP">OWNUP</a></td><td class=clue>Admit cry of distress on picking up wordplay</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/IMPUNITY">IMPUNITY</a></td><td class=clue>No recriminations with this setter's wordplay? Shame, given the execution (8)</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/REBUS">REBUS</a></td><td class=clue>Visual wordplay - Rankin's inspector (5)</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/URANUS">URANUS</a></td><td class=clue>Planet that's the butt of much wordplay (6)</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/PUNJABI">PUNJABI</a></td><td class=clue>Language uses wordplay to needle one (7)</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/PUNISHINGLY">PUNISHINGLY</a></td><td class=clue>"The wordplay is a little off"</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/DRAWNUP">DRAWNUP</a></td><td class=clue>Stopped The Guardian of Wordplay heading north? (5,2)</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td style="font-size:13px"><a href="/crossword-clues/YOUCANTTELLPUNS">YOUCANTTELLPUNS</a></td><td class=clue>Start of a quip about wordplay</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/EVENUP">EVENUP</a></td><td class=clue>First lady returns wordplay to balance teams (4,2)</td></tr>


<tr><td><div class=stars><div></div></div><div class=clear></div></td><td><a href="/crossword-clues/ISIT">ISIT</a></td><td class=clue>Part 1 of a wordplay-related quip</td></tr>


</tbody>
</table>
</body>
</html>
	~
end
