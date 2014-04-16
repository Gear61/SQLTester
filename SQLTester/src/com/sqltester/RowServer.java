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
        									{"Depp", "Political Science", "70000"}}
									   };
	
	public static String[][] getRows(int tableNum)
	{
		return rows[tableNum];
	}
}
