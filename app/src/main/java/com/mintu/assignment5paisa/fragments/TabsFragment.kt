package com.mintu.assignment5paisa.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.mintu.assignment5paisa.R
import com.mintu.assignment5paisa.adapters.watchlistone.WatchListOneAdapter
import com.mintu.assignment5paisa.adapters.watchlistthree.WatchListThreeAdapter
import com.mintu.assignment5paisa.adapters.watchlisttwo.WatchListTwoAdapter
import com.mintu.assignment5paisa.databinding.FragmentWatchListBinding
import com.mintu.assignment5paisa.interfaces.ApplyTheme
import com.mintu.assignment5paisa.models.watchlistone.WatchListOne
import com.mintu.assignment5paisa.models.watchlistone.WatchListOneData
import com.mintu.assignment5paisa.models.watchlistthree.WatchListThree
import com.mintu.assignment5paisa.models.watchlistthree.WatchListThreeData
import com.mintu.assignment5paisa.models.watchlisttwo.WatchListTwo
import com.mintu.assignment5paisa.models.watchlisttwo.WatchListTwoData
import com.mintu.assignment5paisa.utils.Constants
import com.mintu.assignment5paisa.utils.Helpers
import com.mintu.assignment5paisa.utils.SharedPreference
import com.mintu.assignment5paisa.viewModels.WatchListViewModel
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class TabsFragment : Fragment() {

    private lateinit var watchListViewModel: WatchListViewModel
    private var _binding: FragmentWatchListBinding? = null
    private val binding get() = _binding!!
    private var watchListIndex: Int? = null
    private lateinit var switchButton: LinearLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var themeButton: ImageView
    private lateinit var sortByVol: TextView
    private lateinit var sortByPclose: TextView
    private lateinit var sortByTime: TextView
    private lateinit var adapter: WatchListOneAdapter
    private lateinit var adapter2: WatchListTwoAdapter
    private lateinit var adapter3: WatchListThreeAdapter
    private lateinit var isGrid: String
    private lateinit var applyTheme: ApplyTheme
    private var watchListOneDataList = ArrayList<WatchListOneData>()
    private var watchListTwoDataList = ArrayList<WatchListTwoData>()
    private var watchListThreeDataList = ArrayList<WatchListThreeData>()
    private var volFlag: Int = 0
    private var pCloseFlag: Int = 0
    private var lastTimeFlag: Int = 0
    private var mContext: Context? = context
    private var mActivity: Activity? = activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewModelProvider(this)[WatchListViewModel::class.java].apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
            watchListIndex = arguments?.getInt(ARG_SECTION_NUMBER)?.minus(1)
        }

        watchListViewModel = ViewModelProvider(this)[WatchListViewModel::class.java]
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWatchListBinding.inflate(inflater, container, false)
        val root = binding.root

        init(root)
        checkDB()
        changeThemeButtonImage()
        onClicks()
        loadUI()
        updateOnThreeSecond()

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN
                        or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                        or ItemTouchHelper.START or ItemTouchHelper.END
                    ,
                    0)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                try {
                    val from = viewHolder.adapterPosition
                    val to = target.adapterPosition
                    Log.d("watchListIndex",watchListIndex.toString())
                    when(watchListIndex){
                        2-> Collections.swap(watchListOneDataList,from,to)
                        1-> Collections.swap(watchListTwoDataList,from,to)
                        0-> Collections.swap(watchListThreeDataList,from,to)
                    }
                    recyclerView.adapter?.notifyItemMoved(from,to)
                }catch (e: Exception){
//                    Toast.makeText(mContext,"Failed to change! ${e.message}",Toast.LENGTH_SHORT).show()
                }
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            }

            override fun isLongPressDragEnabled(): Boolean {
                return true
            }

        })
        itemTouchHelper.attachToRecyclerView(recyclerView)

        return root
    }

    private fun checkDB() {
        when (watchListIndex) {
            2 -> watchListOneTask()
            1 -> watchListTwoTask()
            0 -> watchListThreeTask()
        }
    }

    private fun watchListOneTask() {
        mContext?.let {
            if (watchListViewModel.getWatchListThreeData(it).isEmpty()) {
                val items: WatchListThree? =
                    watchListViewModel.getWatchListThreeJsonData(it)
                for (item in items?.WatchListThreeData!!) {
                    watchListViewModel.insertWatchThreeData(it, item)
                    watchListThreeDataList.add(item)
                }
            } else {
                watchListThreeDataList =
                    watchListViewModel.getWatchListThreeData(it)
                            as ArrayList<WatchListThreeData>
            }
        }
    }

    private fun watchListTwoTask() {
        mContext?.let {
            if (watchListViewModel.getWatchListTwoData(it).isEmpty()) {
                val items: WatchListTwo? =
                    watchListViewModel.getWatchListSecondJsonData(it)
                for (item in items?.WatchListTwoData!!) {
                    watchListViewModel.insertWatchTwoData(it, item)
                    watchListTwoDataList.add(item)
                }
            } else {
                watchListTwoDataList = watchListViewModel.getWatchListTwoData(it)
                        as ArrayList<WatchListTwoData>
            }
        }
    }

    private fun watchListThreeTask() {
        mContext?.let {
            if (watchListViewModel.getWatchListOneData(it).isEmpty()) {
                val items: WatchListOne? =
                    watchListViewModel.getWatchListOneJsonData(it)
                for (item in items?.WatchListOneData!!) {
                    watchListViewModel.insertWatchOneData(it, item)
                    watchListOneDataList.add(item)
                }
            } else {
                watchListOneDataList = watchListViewModel.getWatchListOneData(it)
                        as ArrayList<WatchListOneData>
            }
        }
    }

    private fun init(root: ConstraintLayout) {
        switchButton = root.findViewById(R.id.switchButton)
        recyclerView = root.findViewById(R.id.watchListRecyclerView)
        themeButton = root.findViewById(R.id.themeButton)
        sortByVol = root.findViewById(R.id.sortByVolume)
        sortByPclose = root.findViewById(R.id.sortByPclose)
        sortByTime = root.findViewById(R.id.sortByTime)
        applyTheme = activity as ApplyTheme
        isGrid = mContext?.let {
            SharedPreference.get(it, Constants.IS_GRID)!!
        }.toString()
        if (isGrid == "true") layoutGrid() else layoutVertical()
    }

    private fun onClicks() {
        themeButton.setOnClickListener {
            changeThemeButtonImage()
            applyTheme.apply()
        }

        switchButton.setOnClickListener {
            val grid: String = if (isGrid == "true") {
                "false"
            } else "true"
            mContext?.let {
                SharedPreference.save(it, Constants.IS_GRID, grid)
                isGrid = SharedPreference.get(it, Constants.IS_GRID)!!
                if (isGrid == "true") layoutGrid() else layoutVertical()
                mActivity?.recreate()
            }
        }

        sortByVol.setOnClickListener {
            onSortVolumeClick()
            mActivity?.recreate()
        }

        sortByPclose.setOnClickListener {
            onSortPCloseClick()
            mActivity?.recreate()
        }

        sortByTime.setOnClickListener {
            onSortTimeClick()
            mActivity?.recreate()
        }
    }

    private fun onSortVolumeClick() {
        setSharedPrefVol()
        pCloseFlag = 0
        lastTimeFlag = 0
        when (watchListIndex) {
            2 -> {
                sortByVolume1()
                sortByVolume2()
                sortByVolume3()
            }
            1 -> {
                sortByVolume3()
                sortByVolume1()
                sortByVolume2()
            }
            0 -> {
                sortByVolume3()
                sortByVolume2()
                sortByVolume1()
            }
        }
    }

    private fun onSortPCloseClick() {
        setSharedPrefPClose()
        volFlag = 0
        lastTimeFlag = 0
        when (watchListIndex) {
            2 -> {
                sortByPClose1()
                sortByPClose2()
                sortByPClose3()
            }
            1 -> {
                sortByPClose3()
                sortByPClose1()
                sortByPClose2()
            }
            0 -> {
                sortByPClose3()
                sortByPClose2()
                sortByPClose1()
            }
        }
    }

    private fun onSortTimeClick() {
        setSharedPrefLastTradeTime()
        volFlag = 0
        pCloseFlag = 0
        when (watchListIndex) {
            2 -> {
                sortByLastTradeTime1()
                sortByLastTradeTime2()
                sortByLastTradeTime3()
            }
            1 -> {
                sortByLastTradeTime3()
                sortByLastTradeTime1()
                sortByLastTradeTime2()
            }
            0 -> {
                sortByLastTradeTime3()
                sortByLastTradeTime2()
                sortByLastTradeTime1()
            }
        }
    }

    private fun setSharedPrefVol() {
        mContext?.let {
            SharedPreference.saveBool(it, Constants.SORT_BY_VOL, true)
            SharedPreference.saveBool(it, Constants.SORT_BY_PCLOSE, false)
            SharedPreference.saveBool(it, Constants.SORT_BY_TIME, false)
        }
    }

    private fun setSharedPrefPClose() {
        mContext?.let {
            SharedPreference.saveBool(it, Constants.SORT_BY_VOL, false)
            SharedPreference.saveBool(it, Constants.SORT_BY_PCLOSE, true)
            SharedPreference.saveBool(it, Constants.SORT_BY_TIME, false)
        }

    }

    private fun setSharedPrefLastTradeTime() {
        mContext?.let {
            SharedPreference.saveBool(it, Constants.SORT_BY_VOL, false)
            SharedPreference.saveBool(it, Constants.SORT_BY_PCLOSE, false)
            SharedPreference.saveBool(it, Constants.SORT_BY_TIME, true)
        }
    }

    private fun clearFilter() {
        mContext?.let {
            SharedPreference.saveBool(it, Constants.SORT_BY_VOL, false)
            SharedPreference.saveBool(it, Constants.SORT_BY_PCLOSE, false)
            SharedPreference.saveBool(it, Constants.SORT_BY_TIME, false)
            SharedPreference.saveBool(it, Constants.VOL_FLAG, false)
            SharedPreference.saveBool(it, Constants.PCLOSE_FLAG, false)
            SharedPreference.saveBool(it, Constants.TIME_FLAG, false)
        }
    }

    /**
     * Sorting Functions By Volume
     */
    private fun sortByVolume1() {
        mContext?.let {
            val sortedList: List<WatchListOneData>
            val isFlag = SharedPreference.getBool(it, Constants.VOL_FLAG)
            sortedList = if (isFlag == true) {
                SharedPreference.saveBool(it, Constants.VOL_FLAG, false)
                Helpers.sortListByVol1Asc(watchListOneDataList)
            } else {
                SharedPreference.saveBool(it, Constants.VOL_FLAG, true)
                Helpers.sortListByVol1Dsc(watchListOneDataList)
            }
            setAdapterWatchList3(sortedList)
        }
    }

    private fun sortByVolume2() {
        mContext?.let {
            val sortedList: List<WatchListTwoData>
            val isFlag = SharedPreference.getBool(it, Constants.VOL_FLAG)
            sortedList = if (isFlag == true) {
                SharedPreference.saveBool(it, Constants.VOL_FLAG, false)
                Helpers.sortListByVol2Asc(watchListTwoDataList)
            } else {
                SharedPreference.saveBool(it, Constants.VOL_FLAG, true)
                Helpers.sortListByVol2Dsc(watchListTwoDataList)
            }
            setAdapterWatchList2(sortedList)
        }
    }

    private fun sortByVolume3() {
        mContext?.let {
            val sortedList: List<WatchListThreeData>
            val isFlag = SharedPreference.getBool(it, Constants.VOL_FLAG)
            sortedList = if (isFlag == true) {
                SharedPreference.saveBool(it, Constants.VOL_FLAG, false)
                Helpers.sortListByVol3Asc(watchListThreeDataList)
            } else {
                SharedPreference.saveBool(it, Constants.VOL_FLAG, true)
                Helpers.sortListByVol3Dsc(watchListThreeDataList)
            }
            setAdapterWatchList1(sortedList)
        }
    }

    /**
     * Sorting Functions By PClose
     */
    private fun sortByPClose1() {
        mContext?.let {
            val sortedList: List<WatchListOneData>
            val isFlag = SharedPreference.getBool(it, Constants.PCLOSE_FLAG)
            sortedList = if (isFlag == true) {
                SharedPreference.saveBool(it, Constants.PCLOSE_FLAG, false)
                Helpers.sortListByPClose1Asc(watchListOneDataList)
            } else {
                SharedPreference.saveBool(it, Constants.PCLOSE_FLAG, true)
                Helpers.sortListByPClose1Dsc(watchListOneDataList)
            }
            setAdapterWatchList3(sortedList)
        }
    }

    private fun sortByPClose2() {
        mContext?.let {
            val sortedList: List<WatchListTwoData>
            val isFlag = SharedPreference.getBool(it, Constants.PCLOSE_FLAG)
            sortedList = if (isFlag == true) {
                SharedPreference.saveBool(it, Constants.PCLOSE_FLAG, false)
                Helpers.sortListByPClose2Asc(watchListTwoDataList)
            } else {
                SharedPreference.saveBool(it, Constants.PCLOSE_FLAG, true)
                Helpers.sortListByPClose2Dsc(watchListTwoDataList)
            }
            setAdapterWatchList2(sortedList)
        }
    }

    private fun sortByPClose3() {
        mContext?.let {
            val sortedList: List<WatchListThreeData>
            val isFlag = SharedPreference.getBool(it, Constants.PCLOSE_FLAG)
            sortedList = if (isFlag == true) {
                SharedPreference.saveBool(it, Constants.PCLOSE_FLAG, false)
                Helpers.sortListByPClose3Asc(watchListThreeDataList)
            } else {
                SharedPreference.saveBool(it, Constants.PCLOSE_FLAG, true)
                Helpers.sortListByPClose3Dsc(watchListThreeDataList)
            }
            setAdapterWatchList1(sortedList)
        }
    }

    /**
     * Sorting Functions By LastTradeTime
     */
    private fun sortByLastTradeTime1() {
        mContext?.let {
            val sortedList: List<WatchListOneData>
            val isFlag = SharedPreference.getBool(it, Constants.TIME_FLAG)
            sortedList = if (isFlag == true) {
                SharedPreference.saveBool(it, Constants.TIME_FLAG, false)
                Helpers.sortListByLastTradeTime1Asc(watchListOneDataList)
            } else {
                SharedPreference.saveBool(it, Constants.TIME_FLAG, true)
                Helpers.sortListByLastTradeTime1Dsc(watchListOneDataList)
            }
            setAdapterWatchList3(sortedList)
        }
    }

    private fun sortByLastTradeTime2() {
        mContext?.let {
            val sortedList: List<WatchListTwoData>
            val isFlag = SharedPreference.getBool(it, Constants.TIME_FLAG)
            sortedList = if (isFlag == true) {
                SharedPreference.saveBool(it, Constants.TIME_FLAG, false)
                Helpers.sortListByLastTradeTime2Asc(watchListTwoDataList)
            } else {
                SharedPreference.saveBool(it, Constants.TIME_FLAG, true)
                Helpers.sortListByLastTradeTime2Dsc(watchListTwoDataList)
            }
            setAdapterWatchList2(sortedList)
        }
    }

    private fun sortByLastTradeTime3() {
        mContext?.let {
            val sortedList: List<WatchListThreeData>
            val isFlag = SharedPreference.getBool(it, Constants.TIME_FLAG)
            sortedList = if (isFlag == true) {
                SharedPreference.saveBool(it, Constants.TIME_FLAG, false)
                Helpers.sortListByLastTradeTime3Asc(watchListThreeDataList)
            } else {
                SharedPreference.saveBool(it, Constants.TIME_FLAG, true)
                Helpers.sortListByLastTradeTime3Dsc(watchListThreeDataList)
            }
            setAdapterWatchList1(sortedList)
        }
    }

    private fun setAdapterWatchList1(data: List<WatchListThreeData>) {
        setLayoutManager()
        setAdapter3(data)
    }

    private fun setAdapterWatchList2(data: List<WatchListTwoData>) {
        setLayoutManager()
        setAdapter2(data)
    }

    private fun setAdapterWatchList3(data: List<WatchListOneData>) {
        setLayoutManager()
        setAdapter1(data)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapter3(data: List<WatchListThreeData>) {
        adapter3 = WatchListThreeAdapter(data, isGrid) { data ->
            val json = Gson().toJson(data)
            showBottomSheet(json)
        }
        recyclerView.adapter = adapter3
        adapter3.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapter2(data: List<WatchListTwoData>) {
        adapter2 = WatchListTwoAdapter(data, isGrid) { data ->
            val json = Gson().toJson(data)
            showBottomSheet(json)
        }
        recyclerView.adapter = adapter2
        adapter2.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapter1(data: List<WatchListOneData>) {
        adapter = WatchListOneAdapter(data, isGrid) { data ->
            val json = Gson().toJson(data)
            showBottomSheet(json)
        }
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun setLayoutManager() {
        if (isGrid != "true") {
            recyclerView.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
        } else {
            recyclerView.layoutManager = GridLayoutManager(
                context, 2,
                GridLayoutManager.VERTICAL, false
            )
        }
    }

    private fun showBottomSheet(data: String) {
        val bottomSheet = BottomSheetFragment(data)
        bottomSheet.show(requireActivity().supportFragmentManager, "BottomSheetDialog")
    }

    @SuppressLint("ResourceAsColor")
    private fun layoutVertical() {
        switchButton.setBackgroundColor(Color.GRAY)
        switchButton.gravity = Gravity.START
    }

    @SuppressLint("ResourceAsColor")
    private fun layoutGrid() {
        switchButton.setBackgroundColor(Color.BLUE)
        switchButton.gravity = Gravity.END
    }

    private fun changeThemeButtonImage() {
        val theme = mContext?.let {
            SharedPreference.get(it, Constants.THEME)
        }
        when (theme!!) {
            "Light" -> {
                Picasso.get().load(R.drawable.dark).into(themeButton)
            }
            "Dark" -> {
                Picasso.get().load(R.drawable.light).into(themeButton)
            }
            else -> {
                Picasso.get().load(R.drawable.light).into(themeButton)
            }
        }

    }

    private fun loadUI() {
        mContext?.let {
            val isVolSorted = SharedPreference.getBool(it, Constants.SORT_BY_VOL)
            val isPCloseSorted = SharedPreference.getBool(it, Constants.SORT_BY_PCLOSE)
            val isTimeSorted = SharedPreference.getBool(it, Constants.SORT_BY_TIME)

            if (isVolSorted == true) {
                sortByVol.setBackgroundColor(Color.RED)
                onSortVolumeClick()
            } else if (isPCloseSorted == true) {
                sortByPclose.setBackgroundColor(Color.RED)
                onSortPCloseClick()
            } else if (isTimeSorted == true) {
                sortByTime.setBackgroundColor(Color.RED)
                onSortTimeClick()
            } else {
                when (watchListIndex) {
                    2 -> setAdapterWatchList3(watchListOneDataList)
                    1 -> setAdapterWatchList2(watchListTwoDataList)
                    0 -> setAdapterWatchList1(watchListThreeDataList)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): TabsFragment {
            return TabsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    fun dataUpdate() {
        val listOne = mContext?.let { watchListViewModel.getWatchListOneData(it) }
                as ArrayList<WatchListOneData>
        val listTwo = mContext?.let { watchListViewModel.getWatchListTwoData(it) }
                as ArrayList<WatchListTwoData>
        val listThree = mContext?.let { watchListViewModel.getWatchListThreeData(it) }
                as ArrayList<WatchListThreeData>

        for (item in listOne) {
            if (Helpers.randomOperatorName() == "+") {
                item.Id?.let {
                    mContext?.let { context ->
                        watchListViewModel.updateLastTradePriceWatchListOne(
                            context, item.LastTradePrice + Helpers.randomNumber(),
                            it
                        )
                        watchListViewModel.updateLastOperatorWatchListOne(
                            context, "+",
                            it
                        )
                    }
                }
            } else {
                item.Id?.let {
                    mContext?.let { context ->
                        watchListViewModel.updateLastTradePriceWatchListOne(
                            context, item.LastTradePrice - Helpers.randomNumber(),
                            it
                        )
                        watchListViewModel.updateLastOperatorWatchListOne(
                            context, "-",
                            it
                        )
                    }
                }
            }
        }

        for (item in listTwo) {
            if (Helpers.randomOperatorName() == "+") {
                item.Id?.let {
                    mContext?.let { context ->
                        watchListViewModel.updateLastTradePriceWatchListTwo(
                            context, item.LastTradePrice + Helpers.randomNumber(),
                            it
                        )
                        watchListViewModel.updateLastOperatorWatchListTwo(
                            context, "+",
                            it
                        )
                    }
                }
            } else {
                item.Id?.let {
                    mContext?.let { context ->
                        watchListViewModel.updateLastTradePriceWatchListTwo(
                            context, item.LastTradePrice - Helpers.randomNumber(),
                            it
                        )
                        watchListViewModel.updateLastOperatorWatchListTwo(
                            context, "-",
                            it
                        )
                    }
                }
            }
        }

        for (item in listThree) {
            if (Helpers.randomOperatorName() == "+") {
                item.Id?.let {
                    mContext?.let { context ->
                        watchListViewModel.updateLastTradePriceWatchListThree(
                            context, item.LastTradePrice + Helpers.randomNumber(),
                            it
                        )
                        watchListViewModel.updateLastOperatorWatchListThree(
                            context, "+",
                            it
                        )
                    }
                }
            } else {
                item.Id?.let {
                    mContext?.let { context ->
                        watchListViewModel.updateLastTradePriceWatchListThree(
                            context, item.LastTradePrice - Helpers.randomNumber(),
                            it
                        )
                        watchListViewModel.updateLastOperatorWatchListThree(
                            context, "-",
                            it
                        )
                    }
                }
            }
        }

        updateAdapters()
    }

    private fun updateAdapters() {
        watchListOneDataList = mContext?.let { watchListViewModel.getWatchListOneData(it) }
                as ArrayList<WatchListOneData>
        watchListTwoDataList = mContext?.let { watchListViewModel.getWatchListTwoData(it) }
                as ArrayList<WatchListTwoData>
        watchListThreeDataList = mContext?.let { watchListViewModel.getWatchListThreeData(it) }
                as ArrayList<WatchListThreeData>

        mContext?.let {
            val isVolSorted = SharedPreference.getBool(it, Constants.SORT_BY_VOL)
            val isPCloseSorted =
                SharedPreference.getBool(it, Constants.SORT_BY_PCLOSE)
            val isTimeSorted = SharedPreference.getBool(it, Constants.SORT_BY_TIME)
            if (isVolSorted == true) {
                sortByVol.setBackgroundColor(Color.RED)
                onSortVolumeClick()
            } else if (isPCloseSorted == true) {
                sortByPclose.setBackgroundColor(Color.RED)
                onSortPCloseClick()
            } else if (isTimeSorted == true) {
                sortByTime.setBackgroundColor(Color.RED)
                onSortTimeClick()
            } else {
                when (watchListIndex) {
                    2 -> setAdapterWatchList1(watchListThreeDataList)
                    1 -> setAdapterWatchList2(watchListTwoDataList)
                    0 -> setAdapterWatchList3(watchListOneDataList)
                }
            }
        }
    }

    private fun updateOnThreeSecond() {
        val timer = object : CountDownTimer(5000, 3000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("FUN: ", "LOG")
                dataUpdate()
            }

            override fun onFinish() {
                updateOnThreeSecond()
            }
        }
        timer.start()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    @Deprecated("Deprecated in Java")
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mActivity = activity
    }
}