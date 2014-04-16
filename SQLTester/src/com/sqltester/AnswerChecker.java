package com.sqltester;

import android.content.Context;

public class AnswerChecker {
	public MisterDataSource dataSource;
	
	public AnswerChecker(Context context) {
		dataSource = new MisterDataSource(context);
	}
	
	public boolean checkAnswer(int questionNumber, String userQuery) {
		String[][] userResult = dataSource.getData(userQuery);
		for (int i = 0; i < userResult.length; i++) {
			for (int j = 0; j < userResult[0].length;j++) {
				System.out.println(userResult[i][j] + " ");
			}
			System.out.println("\n");
		}
		return true;
	}
}
