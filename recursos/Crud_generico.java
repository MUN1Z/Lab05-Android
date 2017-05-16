abstract class BasicRepository<T> {

    protected abstract ContentValues getContentValues(T object);

    protected abstract T FillObject(Cursor cursor);

    protected abstract String getTable();

    protected SQLiteDatabase getDatabase() {
        return SqliteConnection.getInstancia();
    }

    protected void insertList(List<T> list) {

        if (list == null || list.isEmpty()) {
            return;
        }
        for (T t : list) {
            getDatabase().insert(getTable(), null, getContentValues(t));
        }
    }

    protected void insert(T object) {

        getDatabase().insert(getTable(), null, getContentValues(object));

    }

    protected void replace(T object) {

        getDatabase().replace(getTable(), null, getContentValues(object));

    }

    protected void update(T object, String whereClause, String[] whereArgs) {

        getDatabase().update(getTable(), getContentValues(object), whereClause,
                whereArgs);

    }

    protected void delete(Integer id) {

        String[] whereArgs = {id.toString()};
        getDatabase().delete(getTable(), "_id  = ?", whereArgs);

    }

    protected void deleteSql(String whereClause, String[] whereArgs) {

        getDatabase().delete(getTable(), whereClause, whereArgs);

    }

    protected T objectSql(String sql, String[] selectionArgs) {

        Cursor cursor = getDatabase().rawQuery(sql, selectionArgs);
        return cursorToObject(cursor);

    }

    protected T cursorToObject(Cursor cursor) {
        T object = null;

        if (cursor.moveToNext()) {
            object = FillObject(cursor);
        }
        cursor.close();
        return object;
    }

    protected List<T> listAll() {

        return list(null, null, null, null, null, null);

    }

    protected List<T> listAll(String selectionClause, String[] selectionArgs) {

        return list(selectionClause, selectionArgs, null, null, null, null);

    }

    protected List<T> listSql(String sql, String[] selectionArgs) {

        Cursor cursor = getDatabase().rawQuery(sql, selectionArgs);
        return cursorToList(cursor);

    }

    protected List<T> listAll(String orderBy) {
        return list(null, null, null, null, orderBy, null);
    }

    protected List<T> list(String selection, String[] selectionArgs,
                           String groupBy, String having, String orderBy, String limit) {
        Cursor cursor = getDatabase().query(getTable(), null, selection,
                selectionArgs, groupBy, having, orderBy, limit);
        return cursorToList(cursor);
    }

    protected List<T> cursorToList(Cursor cursor) {
        List<T> list = new ArrayList<T>();

        while (cursor.moveToNext()) {
            list.add(FillObject(cursor));
        }
        cursor.close();
        return list;
    }

}
