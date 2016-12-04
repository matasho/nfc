package aero.panasonic.nfcreader;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.nio.charset.Charset;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final byte[] ENGLISH_PLAIN_TEXT = new byte[] {(byte) 0xd1, (byte) 0x01, (byte) 0x08, (byte) 0x55, (byte) 0x01, (byte) 0x63, (byte) 0x6e, (byte) 0x6e, (byte) 0x2e, (byte) 0x63, (byte) 0x6f, (byte) 0x6d};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter == null)
            Log.v(TAG, "NFC not available on device");
    }

    public void sendMessage(View view){
        final Intent intent = new Intent(NfcAdapter.ACTION_TAG_DISCOVERED);
        NdefMessage[] msg;

        try {
            msg = new NdefMessage[]{new NdefMessage(ENGLISH_PLAIN_TEXT)};
        } catch(final Exception e){
            throw new RuntimeException("Cannot parse data", e);
        }

        intent.putExtra(NfcAdapter.EXTRA_NDEF_MESSAGES, msg);

        createRecord();
    }

    private void createRecord(){
        NdefRecord record = NdefRecord.createMime("text/plain", ENGLISH_PLAIN_TEXT);
        System.out.println(Arrays.toString(record.getPayload()));
    }

    @Override
    public void onNewIntent(Intent intent){

    }


}
