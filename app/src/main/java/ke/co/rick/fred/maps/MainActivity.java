package ke.co.rick.fred.maps;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {
   static final LatLng NAIROBI = new LatLng(1.2833, 36.8167);
   static final LatLng MOMBASA = new LatLng(4.0500, 39.6667);

   private GoogleMap mMap; // Might be null if Google Play services APK is not available.
   private int mapType = mMap.MAP_TYPE_NORMAL;
    LocationManager myLocationManager = null;
    Criteria myCriteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpMapIfNeeded();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();

            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(NAIROBI).title("Nairobi").snippet("Nice Place"));
        mMap.addMarker(new MarkerOptions().position(MOMBASA).title("Mombasa").snippet("Better Place"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(NAIROBI, 6));
        mMap.setMapType(mapType);

       mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
           @Override
           public void onMapLongClick(LatLng latLng) {
               AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
               builder.setTitle("CHOOSE ONE")
                       .setMessage("Seriously Choose One")
                       .setCancelable(false)
                       .setIcon(R.drawable.t)
                       .setPositiveButton("Place a pinpoint", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {

                           }
                       })
                       .setNeutralButton("Get address", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {

                           }
                       })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })

               ;  builder.create();
               builder.show();
           }
       });

        // Enable MyLocation Layer of Google Map
        mMap.setMyLocationEnabled(true);

        UiSettings setts = mMap.getUiSettings();
        setts.setZoomControlsEnabled(true);
        setts.setCompassEnabled(true);
        setts.setMyLocationButtonEnabled(true);
        setts.setAllGesturesEnabled(true);


        // Get LocationManager object from System Service LOCATION_SERVICE
       /* LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Create a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Get the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Get Current Location

        Location myLocation = locationManager.getLastKnownLocation(provider);

       // Instantly move camera to Nairobi with a zoom of 15
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NAIROBI, 15));

        // Get latitude of the current location
        double latitude = myLocation.getLatitude();

        // Get longitude of the current location
        double longitude = myLocation.getLongitude();

        // Create a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

        // Show the current location in Google Map
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here!").snippet("Consider yourself located")); */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.normal_map:
                mapType=mMap.MAP_TYPE_NORMAL;
                break;

            case R.id.satellite_map:
                mapType=mMap.MAP_TYPE_SATELLITE;
                break;

            case R.id.terrain_map:
                mapType=mMap.MAP_TYPE_TERRAIN;
                break;

            case R.id.hybrid_map:
                mapType=mMap.MAP_TYPE_HYBRID;
                break;
        }

        mMap.setMapType(mapType);
        return true;
    }
}
