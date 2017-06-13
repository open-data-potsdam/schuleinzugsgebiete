#!/bin/sh
curl 'http://schullandschaft.brandenburg.de/edugis/wfs/schulen/?service=wfs&version=2.0.0&request=GetFeature&typeNames=Schul_Standorte' > schools.xml
