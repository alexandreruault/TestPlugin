package com.github.alexandreruault.testplugin.template.presentationlayer.klass

import android.databinding.tool.ext.toCamelCase
import com.github.alexandreruault.testplugin.manager.PackageManager

fun createPresentationFragment(
    presentationPackageName: String = PackageManager.packageName,
    className: String,
    layounName: String,
    viewModelName: String,
) = """
  package $presentationPackageName
   import android.content.Context
   import android.os.Bundle
   import android.view.LayoutInflater
   import android.view.View
   import android.view.ViewGroup
   import androidx.appcompat.widget.Toolbar
   import com.comuto.R
   import com.comuto.coreui.fragment.PixarFragmentV2
   import com.comuto.di.InjectHelper
   import com.comuto.databinding.${layounName.toCamelCase().replace("_","")}Binding
   import javax.inject.Inject
   
   class $className : PixarFragmentV2() {

      override val title: Int
          get() = TODO()

      @Inject
      lateinit var viewModel: $viewModelName

      private var _binding: ${layounName.toCamelCase().replace("_","")}Binding? = null
      private val binding get() = _binding!!

      private val toolbar: Toolbar
        get() = binding.toolbar.root
        
      override fun getScreenName() = TODO()

      companion object {

          fun newInstance(): $className = ${className.toCamelCase()}()    
              
      }

      override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
          _binding = ${layounName.toCamelCase().replace("_","")}Binding.inflate(inflater, container, false)
          return binding.root
      }

      override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          super.onViewCreated(view, savedInstanceState)
          initObservers()
      }

      

      private fun initObservers() {
          viewModel.liveEvent.observe(viewLifecycleOwner) { event -> onNewEvent(event) }
          viewModel.liveState.observe(viewLifecycleOwner) { state -> onStateUpdated(state) }
      }


      private fun onStateUpdated(state: ${viewModelName}State) {
          when (state) {
            else -> TODO()
          }
      }
      
      private fun onNewEvent(event: ${viewModelName}Event) {
          when (event) {
            else -> TODO()
          }
      }

      override fun injectFragment() {
              InjectHelper.getOrCreateSubcomponent(requireContext(),TODO())
              .TODO()
              .bind(this)
              .bind(requireActivity())
              .build()
              .inject(this)
      }

  }
""".trimIndent()

fun createPresentationViewModel(
    presentationPackageName: String = PackageManager.packageName,
    viewModelName: String
) = """
    package $presentationPackageName

    import androidx.lifecycle.LiveData
    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.comuto.coreui.livedata.SingleLiveEvent
    import kotlinx.coroutines.launch

    class $viewModelName(
        defaultState: ${viewModelName}State = ${viewModelName}State.InitialState
    ) : ViewModel() {


        private val _liveState = MutableLiveData(defaultState)
        val liveState: LiveData<${viewModelName}State> get() = _liveState

        private val _liveEvent = SingleLiveEvent<${viewModelName}Event>()
        val liveEvent: LiveData<${viewModelName}Event> get() = _liveEvent


        fun init() {
            _liveState.value = ${viewModelName}State.InitialState
        }


    }

    sealed class ${viewModelName}State {
        object InitialState : ${viewModelName}State()
    }

    sealed class ${viewModelName}Event {
    }
""".trimIndent()

fun createPresentationViewModelFactory(
    presentationPackageName: String = PackageManager.packageName,
    viewModelName: String
) = """
    package $presentationPackageName

    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.ViewModelProvider
    import javax.inject.Inject

    @Suppress("UNCHECKED_CAST")
    class ${viewModelName}Factory @Inject constructor() : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            ${viewModelName}() as T
    }
    
""".trimIndent()


fun createPresentationSubComponent(
    presentationPackageName: String = PackageManager.packageName,
    injectionPackageName: String = PackageManager.packageName,
    subComponentName :String,
    fragmentName: String,
    viewModelName: String
) = """
package $injectionPackageName


import androidx.fragment.app.FragmentActivity
import ${presentationPackageName}.${fragmentName}
import dagger.BindsInstance
import dagger.Subcomponent


@Subcomponent(modules = [${viewModelName}Module::class])
interface $subComponentName {

    fun inject(fragment: $fragmentName)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun bind(fragment: $fragmentName): Builder

        @BindsInstance
        fun bind(activity: FragmentActivity): Builder

        fun build(): $subComponentName
    }
}
    
""".trimIndent()


fun createPresentationModule(
    presentationPackageName: String = PackageManager.packageName,
    injectionPackageName: String = PackageManager.packageName,
    viewModelName: String,
    fragmentName: String
) = """
package $injectionPackageName

import androidx.lifecycle.ViewModelProvider
import ${presentationPackageName}.$fragmentName
import ${presentationPackageName}.$viewModelName
import ${presentationPackageName}.${viewModelName}Factory
import dagger.Module
import dagger.Provides

@Module
class ${viewModelName}Module {

    @Provides
    fun provide${viewModelName}(
        fragment: $fragmentName,
        factory: ${viewModelName}Factory
    ): $viewModelName=
        ViewModelProvider(fragment, factory)[${viewModelName}::class.java]
}
    
""".trimIndent()


fun createPresentationLayoutXML()=""" 
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/colorBackground"
        >
       
    <include
        android:id="@+id/toolbar"
        layout="@layout/toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        />

    </androidx.constraintlayout.widget.ConstraintLayout>
    
    
""".trimIndent()
