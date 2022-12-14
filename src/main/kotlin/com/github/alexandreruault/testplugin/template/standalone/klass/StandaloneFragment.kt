package com.github.alexandreruault.testplugin.template.standalone.klass

import android.databinding.tool.ext.toCamelCase
import com.github.alexandreruault.testplugin.manager.PackageManager

fun createStandaloneFragment(
    presentationPackageName: String = PackageManager.packageName,
    className: String,
    layountName: String
) = """
  package $presentationPackageName
   import android.content.Context
   import android.os.Bundle
   import android.view.LayoutInflater
   import android.view.View
   import androidx.appcompat.widget.Toolbar
   import android.view.ViewGroup
   import com.comuto.coreui.fragment.PixarFragmentV2
   import com.comuto.databinding.${layountName.toCamelCase().replace("_","")}Binding

   
   class $className : PixarFragmentV2() {

      override val title: Int
          get() = TODO()

      private var _binding: ${className.toCamelCase()}LayoutBinding? = null
      private val binding get() = _binding!!
      
      private val toolbar: Toolbar
        get() = binding.toolbar.root

      override fun getScreenName() = TODO()

      companion object {

          fun newInstance(): $className = ${className.toCamelCase()}()    
              
      }

      override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
          _binding = ${layountName.toCamelCase().replace("_","")}Binding.inflate(inflater, container, false)
          return binding.root
      }

      override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          super.onViewCreated(view, savedInstanceState)
      }

      

  }
""".trimIndent()

fun createStandaloneViewModel(
    packageName: String = PackageManager.packageName,
    viewModelName: String
) = """
    package $packageName

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
