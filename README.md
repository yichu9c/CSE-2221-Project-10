Project: Glossary
Objectives
Familiarity with designing and coding a realistic component-based application program without being provided a skeleton solution.
Note that in your solution you can only use components from the components package and components from the standard Java libraries that have been used before in lectures/labs/projects (e.g., String). You should not use other components from any other libraries that have not been used in CSE 2221.

The Problem
Your customer, Cy Burnett, heads a major publisher of textbooks who is migrating toward on-line access. Cy wants a relatively easy-to-maintain glossary facility. (A glossary is a list of difficult or specialized words, or terms, with their definitions, that is usually near the end of a book.) His initial requirements are as follows:

The output shall be a group of HTML files. There shall be a top-level index which merely lists each term in the glossary, and separate pages for each of the terms with their definitions. Clicking on a term in the index shall take you to the page for that term and its associated definition. Moreover, clicking on any term in the glossary that happens to appear in a definition shall take you to the page for that term and its associated definition.
Every term shall consist of a single "word" (i.e., a term contains no whitespace characters).
The terms shall appear in the glossary index in alphabetical order. In each term's page, that term shall appear in red boldface italics just before its definition.
The entire glossary shall be generated in batch fashion by your program from an input file. This input shall consist of a single term on the first line, its definition on the next one or more lines (terminated by an empty line), another term on the next line, its definition on the next one or more lines (terminated by an empty line), etc. The input shall continue in this fashion through the definition of the last term, which shall end with its terminating empty line. The program shall not check for invalid input; the customer shall be responsible for providing input that meets the stated conditions.
The program shall ask the user for the name of the input file and for the name of a folder where all the output files will be saved. The output folder must already exist and will not be created by the program. The top-level index shall be named index.html and the page for each term shall be named term.html where term is the actual term.
In the preceding item, "name of the input file" and "name of a folder" are to be understood as follows. Each includes the notion of a path to the terminal name. The path may either be relative to a current folder or be absolute from the top of the file system. It would be bad form for the program to insert an implied sub-path or folder prior to or after what the user supplies as input. Similarly, it would be bad form for the program to supply an implied filename extension such as ".txt" after what the user supplies as input. Therefore, the program shall respect the user input as being the complete relative or absolute path as the name of the input file, or the name of a folder, and will not augment the given path in any way, e.g., it will not supply its own filename extension. For example, a reasonable user response for the name of the input file could directly result in the String value "data/terms.txt"; similarly, a reasonable user response for the name of a folder could directly result in the String value "data".
These are the stated requirements for your program. If you have questions of clarification or need additional details, Cy is willing to answer them if you ask your instructor to contact him.

Setup
You're on your own! There are no other setup instructions; this is the "blank sheet of paper" project that you've all been waiting for!
Method
When you are satisfied that your program works, select your Eclipse project (not just some of the files, but the whole project), create a zip archive of it, and submit the zip archive to the Carmen dropbox for this project, as described in Submitting a Project.

Your grade will depend not merely on whether the final program meets the customer's initial requirements, of course, but also on the general software quality factors we've been discussing for the past three months: understandability, precision, appropriate use of existing software components, maintainability, adherence to coding standards, and so forth.

A sample input file is available at:

     terms.txt
A sample of the corresponding program output is available at:

     index.html
You should not assume that your glossary must look like this one, which is merely a sample of what the output might be. This sample is just something your requirements analyst and the customer cobbled together by hand for a small glossary, to serve as a possible prototype for the final program output. You should feel free to improve on it.

Extra Credit (up to 20%)
Develop a JUnit test fixture for the methods you designed in your program and include it in your project submission. Extra credit will be awarded only for a systematically designed, well thought out test plan.

Additional Activities
Here are some questions about possible additional activities related to this project. Any extra work is strictly optional, for your own benefit, and will not directly affect your grade.

What happens if a definition provided in the input includes HTML tags?
What happens if a term occurs, within a definition, in its plural form?  Fix the program so it handles such a case "correctly", i.e., so it introduces a link from that term.
Modify the program to handle multiple-word terms.
Modify the input format and the program so there can be multiple indexes. In particular, allow each term to have not only a definition but a set of other indexes in which it should appear, in addition to the alphabetical master index.
Write a more professional instruction manual (e.g., as an HTML document or using a word processor).
