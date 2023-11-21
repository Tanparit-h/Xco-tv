package com.zicure.xcotv.domain.usecase

import com.zicure.xcotv.utils.AppUtil
import javax.inject.Inject

class GetPackageInfoUseCase @Inject constructor(private val appUtil: AppUtil) {
    operator fun invoke() = appUtil.getPackage()
}