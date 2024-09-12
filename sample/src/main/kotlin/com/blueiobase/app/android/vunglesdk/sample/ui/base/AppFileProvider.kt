package com.blueiobase.app.android.vunglesdk.sample.ui.base

import androidx.core.content.FileProvider
import com.blueiobase.app.android.vunglesdk.sample.R

/**
 * A custom [FileProvider] for the application.
 *
 * This class extends [FileProvider] and is used to provide secure access to files from the application's internal storage to other apps.
 * @author IO DevBlue
 * @since 1.0.0
 */
@Suppress("unused")
class AppFileProvider: FileProvider(R.xml.provider_file_paths) {
    companion object {
        /** The authority used to identify this [FileProvider]. */
        const val AUTHORITY = "com.blueiobase.app.android.vunglesdk.sample.fileprovider"
    }
}