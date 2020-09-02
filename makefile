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
<<<<<<< HEAD
	@$(JAVAC) $(JFLAGS) $<

#default rule envoked by make
QuizGen: load $(classes)
	@java -cp bin QuizGen.Driver

load:
	@echo loading...   Please wait just a minute
=======
	$(JAVAC) $(JFLAGS) $<

#default rule envoked by make
QuizGen: $(classes)
	java -cp bin QuizGen.Driver
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3

#running all our different applications 

#rules for generating documetation 
doc:
	javadoc -d $(DOCDIR) -link http://docs.oracle.com/javase/8/docs/api/ $(SRCDIR)/*.java $(SRCDIR)

clean:
	  @rm -r $(BINDIR)/QuizGen
	  @rm -Rf doc

<<<<<<< HEAD
cleanPools:
	rm MCQuestions/*.txt
	rm TrueFalse/*.txt
=======
cleanMCQs:
	rm MCQuestions/*.txt
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3