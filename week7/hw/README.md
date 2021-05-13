#### optimization with SQL

2. insert 1 million dummy orders, testing the efficiency of different methods on insertion.  
9. Read/Write Splitting, dynamic data source routing v1.0 
10. Read/Write Splitting, database frame v2.0

2. insert 1 million dummy orders, testing the efficiency of different methods on insertion. 
User table as:
```
CREATE TABLE Users(
    user_id INT PRIMARY KEY AUTO_INCREMENT, 
    name VARCHAR(255), 
    login_time TIMESTAMP,
    logout_time TIMESTAMP
    );
```
insert with prepareStatment, addBatch
```
    public static int batchInsert(String tableName, List<Map<String, Object>> datas) throws SQLException {
       
        int affectRowCount = -1;
        try {
           getConnection();
           conn.setAutoCommit(false);

            Map<String, Object> valueMap = datas.get(0);
            Set<String> keySet = valueMap.keySet();
            Iterator<String> iterator = keySet.iterator();
            StringBuilder columnSql = new StringBuilder();
            StringBuilder unknownMarkSql = new StringBuilder();
            Object[] keys = new Object[valueMap.size()];
            int i = 0;
            while (iterator.hasNext()) {
                String key = iterator.next();
                keys[i] = key;
                columnSql.append(i == 0 ? "" : ",");
                columnSql.append(key);

                unknownMarkSql.append(i == 0 ? "" : ",");
                unknownMarkSql.append("?");
                i++;
            }

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ");
            sql.append(tableName);
            sql.append(" (");
            sql.append(columnSql);
            sql.append(" )  VALUES (");
            sql.append(unknownMarkSql);
            sql.append(" )");

            pstmt = conn.prepareStatement(sql.toString());
            for (int j = 0; j < datas.size(); j++) {
                for (int k = 0; k < keys.length; k++) {
                    pstmt.setObject(k + 1, datas.get(j).get(keys[k]));
                }
                pstmt.addBatch();
            }
            int[] arr = pstmt.executeBatch();
            
            conn.commit();
            affectRowCount = arr.length;
        } catch (Exception e) {
           e.printStackTrace();
           jdbcLog.error("error: "+e);
            if (conn != null) {
                conn.rollback();
            }
            // throw e;
        } finally {
           close(conn, pstmt, rs);
        }
        return affectRowCount;
    }
```