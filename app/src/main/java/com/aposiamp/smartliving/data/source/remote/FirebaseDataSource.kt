package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.model.DeviceDataDTO
import com.aposiamp.smartliving.data.model.RoomDataDTO
import com.aposiamp.smartliving.data.model.SpaceDataDTO
import com.aposiamp.smartliving.data.utils.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class FirebaseDataSource(
    private val firebaseAuth: FirebaseAuth,
    private val firebase: FirebaseDatabase
) {
    val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    suspend fun login(email: String, password: String): FirebaseUser {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return result.user ?: throw Exception("Login failed")
    }

    suspend fun signup(email: String, password: String): FirebaseUser {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        return result.user ?: throw Exception("Signup failed")
    }

    fun logout() {
        firebaseAuth.signOut()
    }

    suspend fun forgotPassword(email: String) {
        firebaseAuth.sendPasswordResetEmail(email).await()
    }

    suspend fun updateEmail(email: String) {
        firebaseAuth.currentUser?.verifyBeforeUpdateEmail(email)?.await()
    }

    suspend fun updatePassword(password: String) {
        firebaseAuth.currentUser?.updatePassword(password)?.await()
    }

    suspend fun deleteUser() {
        firebaseAuth.currentUser?.delete()?.await()
    }

    suspend fun setSpaceData(userId: String, spaceDataDTO: SpaceDataDTO) {
        firebase.getReference("spaces").child(userId).setValue(spaceDataDTO).await()
    }

    suspend fun getSpaceData(userId: String): SpaceDataDTO {
        val snapshot = firebase.getReference("spaces").child(userId).get().await()
        val spaceDataDTO = snapshot.getValue(SpaceDataDTO::class.java)
        return spaceDataDTO ?: throw Exception("Space data not found")
    }

    suspend fun checkIfSpaceDataExists(userId: String): Boolean {
        val snapshot = firebase.getReference("spaces").child(userId).get().await()
        return snapshot.exists()
    }

    suspend fun setRoomData(userId: String, spaceId: String, roomDataDTO: RoomDataDTO) {
        val reference = firebase.getReference("rooms").child(userId).child(spaceId).push()
        reference.setValue(roomDataDTO).await()
    }

    suspend fun getRoomList(userId: String, spaceId: String): List<RoomDataDTO>? {
        val snapshot = firebase.getReference("rooms").child(userId).child(spaceId).get().await()

        if (!snapshot.exists() || !snapshot.hasChildren()) {
            return null
        }

        val roomList = mutableListOf<RoomDataDTO>()
        for (roomSnapshot in snapshot.children) {
            val roomId = roomSnapshot.key
            val roomName = roomSnapshot.child("room_name").getValue(String::class.java)
            if (roomId != null && roomName != null) {
                roomList.add(RoomDataDTO(roomId, roomName))
            }
        }
        return roomList
    }

    suspend fun checkIfAnyRoomExists(userId: String, spaceId: String): Boolean {
        val snapshot = firebase.getReference("rooms").child(userId).child(spaceId).get().await()
        return snapshot.exists()
    }

    suspend fun setDeviceData(userId: String, spaceId: String, roomId: String, deviceDataDTO: DeviceDataDTO) {
        val deviceDataMap = mapOf(
            "device_name" to deviceDataDTO.deviceName,
            "device_type" to deviceDataDTO.deviceType
        )
        firebase.getReference("devices").child(userId).child(spaceId).child(roomId).child(deviceDataDTO.deviceId).setValue(deviceDataMap).await()
    }
}