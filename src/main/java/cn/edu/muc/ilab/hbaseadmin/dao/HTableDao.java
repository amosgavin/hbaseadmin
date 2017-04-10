package cn.edu.muc.ilab.hbaseadmin.dao;

import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.protobuf.generated.HBaseProtos;
import org.apache.hadoop.hbase.util.Pair;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Raymond on 2017/3/25.
 */
public interface HTableDao {
    boolean tableExists(TableName tableName);

    boolean tableExists(String tableName);

    HTableDescriptor[] listTables();

    HTableDescriptor[] listTables(Pattern pattern);

    HTableDescriptor[] listTables(String regex);


    void createTables(@NotNull String tableName, @NotNull String[] columnFamilies);

    void createTableAsync(@NotNull String tableName, @NotNull String[] columnFamilies, @NotNull byte[][] splitKeys);

    void deleteTables(String[] tableNameList);

    TableName[] listTableNames(final Pattern pattern, final boolean includeSysTables);

    void enableTable(final TableName tableName);

    void enableTableAsync(final TableName tableName);

    void disableTable(final TableName tableName);

    void disableTableAsync(final TableName tableName);

    boolean isTableEnabled(TableName tableName);

    boolean isTableDisabled(TableName tableName);

    boolean isTableAvailable(TableName tableName);

    Pair<Integer, Integer> getAlterStatus(final TableName tableName);

    void addColumn(final TableName tableName, final HColumnDescriptor column);

    void deleteColumn(final TableName tableName, final byte[] columnName);

    void modifyColumn(final TableName tableName, final HColumnDescriptor descriptor);

    void closeRegion(final byte[] regionName, final String serverName);

    void closeRegion(final ServerName sn, final HRegionInfo hri);

    boolean closeRegionWithEncodedRegionName(final String encodedRegionName, final String serverName);

    void truncateTable(final TableName tableName, final boolean preserveSplits);

    void flushRegion(final byte[] regionName);

    void flush(final TableName tableName);

    void compact(final TableName tableName);

    void compact(final TableName tableName, final byte[] columnFamily);

    void compactRegion(final byte[] regionName);

    void compactRegion(final byte[] regionName, final byte[] columnFamily);

    void compactRegionServer(final ServerName sn, boolean major);

    void majorCompact(final TableName tableName);

    void majorCompact(final TableName tableName, final byte[] columnFamily);

    void majorCompactRegion(final byte[] regionName);

    void majorCompactRegion(final byte[] regionName, final byte[] columnFamily);

    void split(final TableName tableName, final byte[] splitPoint);

    void splitRegion(final byte[] regionName, final byte[] splitPoint);

    void modifyTable(final TableName tableName, final HTableDescriptor htd);

    void snapshot(final String snapshotName, final TableName tableName, HBaseProtos.SnapshotDescription.Type type);
    void snapshot(HBaseProtos.SnapshotDescription snapshot);

    void restoreSnapshot(final String snapshotName, boolean takeFailSafeSnapshot);

    void cloneSnapshot(final String snapshotName, final TableName tableName);

    List<HBaseProtos.SnapshotDescription> listSnapshots();
    List<HBaseProtos.SnapshotDescription> listSnapshots(Pattern pattern);

    void deleteSnapshot(final String snapshotName);
    void deleteSnapshots(final Pattern pattern);


}
