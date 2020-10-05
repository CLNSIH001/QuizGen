# takaedza Chigwedere

SRCDIR = src
DOCDIR = doc
BINDIR = bin
TEXTDIR = text

JAVAC = javac
JFLAGS = -g -d $(BINDIR) -cp $(BINDIR)

vpath %.java $(SRCDIR)
vpath %.class $(BINDIR)
vpath %.text $(TEXTDIR)

# the general build rule for java sources
.SUFFIXES: .java .class

.java.class:
	@$(JAVAC) $(JFLAGS) $<

real: echo default

echo:
	echo loading...   Please wait just a minute

#default rule envoked by make
default:BinaryTreeNode.class BTQueueNode.class  BTQueue.class  BinaryTree.class BinaryHeap.class BinarySearchTree.class AVLTree.class \
		BPTrees.class Graph.class HashTable.class MaxHeap.class \
		MinHeap.class RedBlackTree.class Driver.class;

#running all my different applications 

run:
	@java -cp bin Driver


#rules for generating documetation 
doc:
	javadoc -d $(DOCDIR) -link http://docs.oracle.com/javase/8/docs/api/ $(SRCDIR)/*.java $(SRCDIR)

clean:
	  @rm -r $(BINDIR)
	  @rm -Rf doc

cleanPools:
	rm -r MultipleChoice/*Tree/*.txt MultipleChoice/Graph/*.txt
	rm -r TrueFalse/*Tree/*.txt
	rm -r FillIn/*Tree/*.txt