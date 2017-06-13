#!/bin/bash -e
#
# Convert schools XML from WFS into CSV with school ID and WGS84 coordinates
#

xmlstarlet select --text --template --match //ms:Schul_Standorte --value-of ms:schul_nr --output $'\t' --value-of ms:msGeometry/gml:Point/gml:pos --nl schools.xml > schoolid_coordinate_epsg25833.csv
schoolid=$(mktemp)
coord25833=$(mktemp)
coord4326=$(mktemp)
cut -f 1 schoolid_coordinate_epsg25833.csv > $schoolid
cut -f 2 schoolid_coordinate_epsg25833.csv > $coord25833
gdaltransform -s_srs EPSG:25833 -t_srs EPSG:4326 -output_xy < $coord25833 > $coord4326
paste $schoolid $coord4326 > schoolid_coordinate_epsg4326.csv
