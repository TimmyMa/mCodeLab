package tech.mlaboratory.mcodelab.nativelib

import android.os.Bundle
import android.widget.TextView
import com.ifeng.daemon.facade.NativeSecureparam
import com.ziroom.commonlibrary.util.SignKeyUtil
import tech.mlaboratory.mcodelab.BaseActivity
import tech.mlaboratory.mcodelab.R

class NativeLibActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native)

        findViewById<TextView>(R.id.text).apply {
            append("Key :")
            append((SignKeyUtil.getKey() ?: "") + "\n")

            append("HouseKey :")
            append((SignKeyUtil.getHouseKey() ?: "") + "\n")

            append("SignKey :")
            append(SignKeyUtil.getSignKey() ?: "" + "\n")

            append("readMD5Key :" )
            append(NativeSecureparam.readMD5Key() ?: "" + "\n")

            append("readPacketPublicKey :" )
            append(NativeSecureparam.readPacketPublicKey() ?: "" + "\n")

            append("readPacketSalt :" )
            append(NativeSecureparam.readPacketSalt() ?: "" + "\n")

            append("readSig2Key :" )
            append(NativeSecureparam.readSig2Key() ?: "" + "\n")

            append("readUserCreditPublicKey :" )
            append(NativeSecureparam.readUserCreditPublicKey() ?: "" + "\n")

            append("readUserCreditSalt :" )
            append(NativeSecureparam.readUserCreditSalt() ?: "" + "\n")
        }
    }
}
