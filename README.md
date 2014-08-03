SQLTester
=========

<b>How to Add a Question (And Answer)</b>

Go to <b>QuestionServer.java</b> and update the <b>questionTablePairings</b> and <b>questions</b> array. The questions array is an array of strings that are the questions themselves. The questionTablePairings array is an array of arrays of integers. Each array of integers represents the tables that a question requires (we need this for questions involving joins).

There's probably a better way of doing this, but right now, everything is hard-coded by position. This means that the first entry of the questionTablePairings corresponds to the first entry in the questions array and so on and so forth. Similarly, the integers in questionTablePairings represent the array indices of whatever tables the question needs. So if a question needs the 1st, 2nd, and 4th tables, its questionTablePairings entry is  {0, 1, 3}. To figure out what positions all the tables are in, go to SchemaServer.java and look at the tableNames array.

Adding the answer is a lot easier. Simply update the answers array in <b>AnswerServer.java</b>. Answers and questions are linked by position like before.

<b>How to Add a Table</b>

Go to <b>SchemaServer.java</b> and update <b>tableNames</b> and <b>tableColumns</b>, which is an array of arrays of columns (a class I created to represent a column). Like before, everything is mapped by position and use your brain to figure out how to format your addition to tableColumns. 

NOTE: After creating a table, it needs data, even if it should start off with none. Make sure it at least has an empty array to start with. You will understand more after getting to the next section.

<b>How to Add Data to a Table</b>

Go to <b>RowServer.java</b>, and look for the 3-dimensional array of strings named <b>rows</b> (each tuple is an array, each table is an array of tuples, and the structure as a whole is an array of tables). If you're adding data to an existing table, find its data by position (as always) and add to it. If you're adding data for a new table, add another 2-D array of strings to the mix.

In the end, this is all very easy; you just need to get used to the formatting. Start off by adding really simple questions, answers, and tables and messing around with the app afterwards to see what interacts with what. Changing data is very similar to adding data; in the end, the experience should be very intuitive.
