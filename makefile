#Group2

SRCDIR = src
DOCDIR = doc
BINDIR = bin
TEXTDIR = text

JAVAC = javac
JFLAGS = -d $(BINDIR) -cp $(BINDIR)

need := $(wildcard src/*Node.java)
need += $(filter-out src/BinarySearchTree.java $(need), $(wildcard src/B*e.java))
dataStr := $(filter-out src/Driver.java $(need), $(wildcard src/*.java))
classes := $(need:%.java=%.class) $(dataStr:%.java=%.class) src/Driver.class

vpath %.java $(SRCDIR)
vpath %.class $(BINDIR)
vpath %.text $(TEXTDIR)

# the general build rule for java sources
.SUFFIXES: .java .class

.java.class:
	$(JAVAC) $(JFLAGS) $<

#default rule envoked by make
QuizGen: $(classes)
	java -cp bin QuizGen.Driver

#running all our different applications 

#rules for generating documetation 
doc:
	javadoc -d $(DOCDIR) -link http://docs.oracle.com/javase/8/docs/api/ $(SRCDIR)/*.java $(SRCDIR)

clean:
	  @rm -r $(BINDIR)/QuizGen
	  @rm -Rf doc

cleanMCQs:
	rm MCQuestions/*.txt
