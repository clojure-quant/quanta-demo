#!/bin/bash

echo "deleting duckdb and both overviewdbs"

rm .data/overviewdb-compressing -r
rm .data/overviewdb -r
rm .data/duckdb/bars.ddb.wal
rm .data/duckdb/bars.ddb
