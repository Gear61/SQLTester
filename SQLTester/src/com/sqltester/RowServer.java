package com.sqltester;

public class RowServer
{
	// Database rows stored here in this ghetto hard-coded array
	private static String[][][] rows = {
											{},
											{{"Zaniolo", "Computer Science", "130000"},
    									  	{"Eggert", "Computer Science", "170000"},
    									  	{"Cho", "Computer Science", "150000"},
    									  	{"Fowler", "Anthropology", "80000"},
        									{"Wertheim", "Anthropology", "95000"},
        									{"Yang", "Anthropology", "120000"},
        									{"Kreger", "Political Science", "190000"},
        									{"Saverin", "Political Science", "90000"},
        									{"Chen", "Electrical Engineering", "125000"},
                                            {"White", "Management", "150000"},
                                            {"Calderon", "Journalism", "200000"},
                                            {"Lee", "Computer Science", "250000"},
                                            {"Jacob", "Biology", "175000"},
                                            {"Ng", "Sociology", "50000"},
                                            {"Hsieh", "Chemical Engineering", "400000"},
                                            {"Muniain", "Spanish", "160000"},
                                            {"Guerin", "Management", "500000"}}
									   };
	
	public static String[][] getRows(int tableNum)
	{
		return rows[tableNum];
	}
}
