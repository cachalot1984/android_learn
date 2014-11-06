package org.teahouse.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	public static final String EXTRA_ANSWER = "com.tahouse.android.geoquiz.answer";
	public static final String EXTRA_ANSWER_SHOWN = "com.tahouse.android.geoquiz.answer_shown";
	
	private boolean mAnswer = false;
	private TextView mAnswerTextView;
	private Button mShowButton;

	private void setAnswerShownResult(boolean isAnswerShown) {
		Intent i = new Intent();
		i.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, i);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		
		mAnswer = getIntent().getBooleanExtra(EXTRA_ANSWER, false);
		
		setAnswerShownResult(false);
		
		mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
		mShowButton = (Button)findViewById(R.id.showAnswerButton);
		mShowButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mAnswer) {
					mAnswerTextView.setText(R.string.true_button);
				} else {
					mAnswerTextView.setText(R.string.false_button);
				}
				
				setAnswerShownResult(true);
			}
		});
	}

}
