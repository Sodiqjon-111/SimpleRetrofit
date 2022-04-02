package uz.sodiqjon.simpleretrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import uz.sodiqjon.simpleretrofit.adapters.HomePagerAdapter

val pagersArrayLogin = arrayOf(
    "bir",
    "ikki",
    "uch",
)

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = HomePagerAdapter(childFragmentManager, lifecycle)
        loginViewPager.adapter = adapter
        //loginViewPager.isUserInputEnabled = false
        TabLayoutMediator(loginPagertTab, loginViewPager) { tab, position ->
            tab.text = pagersArrayLogin[position]
        }.attach()

    }


}