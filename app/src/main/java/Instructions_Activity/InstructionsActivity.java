package Instructions_Activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.arunn.silfraagri.MainActivity;
import com.example.arunn.silfraagri.R;

public class InstructionsActivity extends TutorialActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(new PermissionStep.Builder().setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}).setTitle(getString(R.string.permission_title)).setContent(getString(R.string.permission_detail)).setBackgroundColor(Color.parseColor("#800080")).setDrawable(R.drawable.ss_1).setSummary(getString(R.string.continue_and_learn)).build());
        addFragment(new Step.Builder().setTitle(getString(R.string.automatic_data)).setContent(getString(R.string.gm_finds_photos)).setBackgroundColor(Color.parseColor("#4D4DFF")).setDrawable(R.drawable.ss_5).setSummary(getString(R.string.continue_and_learn)).build());
        addFragment(new Step.Builder().setTitle(getString(R.string.choose_the_song)).setContent(getString(R.string.swap_to_the_tab)).setBackgroundColor(Color.parseColor("#000080")).setDrawable(R.drawable.ss_2).setSummary(getString(R.string.continue_and_update)).build());
        addFragment(new Step.Builder().setTitle(getString(R.string.edit_data)).setContent(getString(R.string.update_easily)).setBackgroundColor(Color.parseColor("#007f00")).setDrawable(R.drawable.ss_3).setSummary(getString(R.string.continue_and_result)).build());
        addFragment(new Step.Builder().setTitle(getString(R.string.result_awesome)).setContent(getString(R.string.after_updating)).setBackgroundColor(Color.parseColor("#ff0000")).setDrawable(R.drawable.ss_4).setSummary(getString(R.string.thank_you)).build());

    }

    @Override
    public void finishTutorial() {
        Intent mainIntent = new Intent( InstructionsActivity.this,MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);

    }
}