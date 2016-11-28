# Mac Open Files shim

This library exists only to avoid having to compile Scaled against the com.apple.eawt classes,
which can only be done on a Mac. So we provide a binary Maven dependency that Scaled can use to
listen for Mac open files events without rendering it uncompilable on every other platform.

Go Java for not providing a cross platform way to listen for OS "please open this file"
notifications.
