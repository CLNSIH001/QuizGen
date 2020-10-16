#Group2

SRCDIR = src
DOCDIR = doc
BINDIR = bin
TEXTDIR = text

JAVAC = javac
JFLAGS = -d $(BINDIR) -cp $(BINDIR)

need := $(wildcard src/DataStructures/*Node.java)
need += $(filter-out src/DataStructures/BinarySearchTree.java $(need), $(wildcard src/DataStructures/B*e.java))
dataStr := $(filter-out $(need), $(wildcard src/DataStructures/*.java))
classes = $(need:%.java=%.class) $(dataStr:%.java=%.class) src/HashTableDriver.class src/Driver.class
classes := $(patsubst src/%, %, $(classes))

vpath %.java $(SRCDIR)
vpath %.class $(BINDIR)
vpath %.text $(TEXTDIR)

# the general build rule for java sources
.SUFFIXES: .java .class

.java.class:
	@$(JAVAC) $(JFLAGS) $<

#default rule envoked by make
QuizGen: load $(classes)
	@java -cp bin Driver

load:
	@echo loading...   Please wait just a minute


tests = $(filter-out UnitTests/DataStructures/DataStructuresTest.java, $(wildcard src/UnitTests/*.java))
tests := $(tests:%.java=%.class) $(patsubst src/%, %, $(tests)) DataStructuresTest.class

Test: $(tests)
	@java -cp bin DataStructuresTest

#running all our different applications 

#rules for generating documetation 
doc:
	javadoc -d $(DOCDIR) -link http://docs.oracle.com/javase/8/docs/api/ $(SRCDIR)/*.java $(SRCDIR)

clean:
	  @rm -r $(BINDIR)/DataStructures bin/*.class
	  @rm -Rf doc

cleanPools:
	rm */*Tree/*.txt */Graph/*.txt */BinaryHeap/*Heap/*.txt