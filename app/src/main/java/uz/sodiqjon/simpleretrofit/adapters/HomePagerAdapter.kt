package uz.sodiqjon.simpleretrofit.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.sodiqjon.simpleretrofit.ui.Bir
import uz.sodiqjon.simpleretrofit.ui.Ikki
import uz.sodiqjon.simpleretrofit.ui.Uch

private const val NUM_TABS = 3

class HomePagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return Bir()
            1 -> return Ikki()
        }
        return Uch()
    }
}