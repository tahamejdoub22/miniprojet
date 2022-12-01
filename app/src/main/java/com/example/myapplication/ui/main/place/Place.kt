import com.google.android.gms.maps.model.LatLng




data class Place(
    val name: String,
    val latLng: LatLng,
    val address: String,
    val rating: Float
) : ClusterItem() {
    override fun getPosition(): LatLng =
        latLng

    override fun getTitle(): String =
        name

    override fun getSnippet(): String =
        address
}

abstract class ClusterItem {

    /**
     * The position of this marker. This must always return the same value.
     */
    abstract fun getPosition(): LatLng

    /**
     * The title of this marker.
     */
    abstract fun getTitle(): String

    /**
     * The description of this marker.
     */
    abstract fun getSnippet(): String

}
