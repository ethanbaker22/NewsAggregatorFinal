//package com.example.newsaggregator.data
//
//import android.util.Log
//import com.google.firebase.database.*
//import java.lang.Exception
//import java.util.*
//import kotlin.collections.ArrayList
//
///**
// *
// */
//object UsersModel : Observable() {
//
//    /**
//     *
//     */
//    private var mValueDataListener: ValueEventListener? = null
//    private var mUsersList: ArrayList<Users>? = ArrayList()
//
//    private fun getDatabaseRef(): DatabaseReference? {
//        return FirebaseDatabase.getInstance().reference.child("Users")
//    }
//
//    /**
//     *
//     */
//    init {
//        if (mValueDataListener != null) {
//            getDatabaseRef()?.removeEventListener(mValueDataListener!!)
//        }
//        mValueDataListener = null
//        Log.i("UsersModel", "data init line 24")
//
//        mValueDataListener = object : ValueEventListener {
//            override fun onCancelled(error: DatabaseError) {
//                if (error != null) {
//                    Log.i("UsersModel", "line 28 data update cancelled err = ${error.message}")
//                }
//            }
//
//            /**
//             *
//             */
//            override fun onDataChange(snapshot: DataSnapshot) {
//                try {
//                    Log.i("UsersModel", "data updated line 32")
//                    val data: ArrayList<Users> = ArrayList()
//                    if (snapshot != null) {
//                        for (dataSnapshot: DataSnapshot in snapshot.children) {
//                            try {
//                                data.add(Users(dataSnapshot))
//                            } catch (e: Exception) {
//                                e.printStackTrace()
//                            }
//                        }
//                        mUsersList = data
//                        Log.i(
//                            "UsersModel",
//                            "data updated line 43" + mUsersList!!.size + " in the cache"
//                        )
//                        setChanged()
//                        notifyObservers()
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        }
//        getDatabaseRef()?.addValueEventListener(mValueDataListener as ValueEventListener)
//    }
//
//    fun getData(): ArrayList<Users>? {
//        return mUsersList
//    }
//}