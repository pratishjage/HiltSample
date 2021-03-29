package com.app.daggersample.base.utils

class AlreadyExistException(val reason: String?) : Throwable(message = reason)

class ShowSnakBarException(val reason: String?) : Throwable(message = reason)
class ShowDialogException(val reason: String?) : Throwable(message = reason)
class CriticalException(val reason: String?) : Throwable(message = reason)
class UnMappedNetworkException(val reason: String?) : Throwable(message = reason)
