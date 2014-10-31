package org.teahouse.android.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private static final String TAG = "QuizActivity";
	private static final String QIDX = "qidx";
	private Button mTrueButton;
	private Button mFalseButton;
	private ImageButton mNextButton;
	private ImageButton mPrevButton;
	private TextView mQuestionTextView;
	
	private TrueFalse mQuestionBank[] = new TrueFalse[] {
		new TrueFalse(R.string.question_africa, false),
		new TrueFalse(R.string.question_americas, true),
		new TrueFalse(R.string.question_asia, true),
		new TrueFalse(R.string.question_mideast, false),
		new TrueFalse(R.string.question_oceans, true),
	};
	
	private int mCurrentIndex = 0;
	
	private void updateQuestion() {
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	
	private void checkAnswer(boolean userPressedTrue) {
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
		int messageResId = 0;
		
		if (userPressedTrue == answerIsTrue) {
			messageResId = R.string.correct_toast;
		} else {
			messageResId = R.string.incorrect_toast;
		}
		
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called, savedInstanceState");
        setContentView(R.layout.activity_main);
        
        if(savedInstanceState != null) {
        	mCurrentIndex = savedInstanceState.getInt(QIDX, 0);
        	Log.d(TAG, "restored mCurrentIndex");
        }
        
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updateQuestion();
			}
		});
        updateQuestion();
        
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				checkAnswer(true);
			}
		});
        
        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				checkAnswer(false);
			}
		});
        
        mPrevButton = (ImageButton)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCurrentIndex = mCurrentIndex - 1;
				if (mCurrentIndex < 0)
					mCurrentIndex = mQuestionBank.length - 1;
				updateQuestion();
			}
		});
        
        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updateQuestion();
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart() called");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume() called");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause() called");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onStop() called");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy() called");
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		Log.i(TAG, "onSaveInstanceState() called");
		savedInstanceState.putInt(QIDX, mCurrentIndex);
	}

}
