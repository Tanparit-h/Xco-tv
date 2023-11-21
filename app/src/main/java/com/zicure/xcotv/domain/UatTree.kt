package com.zicure.xcotv.domain

import android.util.Log
import com.zicure.xcotv.domain.usecase.GetPackageInfoUseCase
import com.zicure.xcotv.utils.BaseTree
import timber.log.BuildConfig
import javax.inject.Inject

@OptIn(ExperimentalStdlibApi::class)
class UatTree @Inject constructor(
//    private val getPackageInfoUseCase: GetPackageInfoUseCase
) : BaseTree() {

    override val fqcnIgnore: List<String>
        get() = super.fqcnIgnore.plus(this::class.java.name)

    //    private fun getAppVersion(): String =
//        "ZICURE(${getPackageInfoUseCase().versionName})"
    override fun getPackageName(): String = BuildConfig.APPLICATION_ID

    override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
        // TODO: handle write log to file
    }


}