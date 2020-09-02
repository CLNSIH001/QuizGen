# Quiz Generator (QuizGen)
Vula’s Tests and Quizzes facility has great promise as an educational tool, and even more so when
students in a course are presented with different but equivalent questions. Unfortunately creating
test/quiz questions is very time-consuming. This project will allow CSC2001F lecturers to have
questions on data structures generated for them automatically. These questions will test the students’
ability to insert and delete items into various data structures covered in that course. The data structure
items will be randomly generated so that individuals get different versions of the same question. Code to
find the correct result of inserts and deletes can be taken from the CSC2001F prescribed textbook or
other sources. In addition to providing a user interface for the lecturer to examine generated queries, the
system must also be able to output the questions in a text file that is formatted as required for uploading
to Vula’s Test & Quizzes component.
#Sonia Berman
#sonia@cs.uct.ac.za

Once you hav opened the QuizGen_Group2 folder run the makefile.
At the moment it takes about a minute for everything to compile.
As it stands right now, the user interface is in the terminal.
The menu is a rought idea of how the final product could behave.

The history option has not been implemented yet, but will allow
the user to choose a previously generated pool.
Fill in numeric is also not working.
The AVL and Binary Search Tree are fully operational.
Red Black Tree can't be used for deletion yet.
The Binary Heap is not yet functional.

>make clean (empties the bin directory.)
>make cleanPools (removes every type of question pool stored in the folder)

source code can be found in src
classes in bin
MCQs inside the MCQuestions folder
t/f questions inside the TrueFalse directory
