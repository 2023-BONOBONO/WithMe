package com.bonobono.presentation.viewmodel

import android.system.Os.remove
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Article
import com.bonobono.domain.model.community.Link
import com.bonobono.domain.usecase.community.DeleteArticleUseCase
import com.bonobono.domain.usecase.community.GetArticleByIdUseCase
import com.bonobono.domain.usecase.community.GetArticleListUseCase
import com.bonobono.domain.usecase.community.UpdateArticleLikeUseCase
import com.bonobono.domain.usecase.community.WriteArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val getArticleList: GetArticleListUseCase,
    private val getArticleById: GetArticleByIdUseCase,
    private val writeArticle: WriteArticleUseCase,
    private val updateArticleLike: UpdateArticleLikeUseCase,
    private val deleteArticle: DeleteArticleUseCase
): ViewModel() {

    private val _articleState = MutableStateFlow<NetworkResult<List<Article>>>(NetworkResult.Loading)
    val articleState = _articleState.asStateFlow()

    private val _articleDetailState = MutableStateFlow<NetworkResult<Article>>(NetworkResult.Loading)
    val articleDetailState = _articleDetailState.asStateFlow()

    private val _writeArticleState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Loading)
    val writeArticleState = _writeArticleState.asStateFlow()

    private val _articleLikeState = MutableStateFlow(Unit)
    val articleLikeState = _articleLikeState.asStateFlow()

    private val _deleteArticleState = MutableStateFlow(Unit)
    val deleteArticleState = _deleteArticleState.asStateFlow()

    // 링크 리스트
    private val _linkList = mutableStateListOf<Link>()
    val linkList = _linkList

    fun addLink(link: Link) {
        _linkList.add(link)
    }

    fun removeLink(link: Link) {
        _linkList.remove(link)
    }

    fun getArticleList(type: String) = viewModelScope.launch {
        _articleState.emit(getArticleList.invoke(type))
    }

    fun getArticleById(type: String, articleId: Int) = viewModelScope.launch {
        _articleDetailState.emit(getArticleById.invoke(type, articleId))
    }

    fun writeArticle(type: String, images: List<String>?, article: Article) = viewModelScope.launch {
        _writeArticleState.emit(writeArticle.invoke(type, images, article))
    }

    fun updateArticleLike(type: String, articleId: Int) = viewModelScope.launch {
        _articleLikeState.emit(updateArticleLike.invoke(type, articleId))
    }

    fun deleteArticle(type: String, articleId: Int) = viewModelScope.launch {
        _deleteArticleState.emit(deleteArticle.invoke(type, articleId))
    }
}