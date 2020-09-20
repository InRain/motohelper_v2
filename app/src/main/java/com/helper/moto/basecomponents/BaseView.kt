package com.helper.moto.basecomponents

interface BaseView<BasePresenter> {
    fun setPresenter(presenter: BasePresenter);
}