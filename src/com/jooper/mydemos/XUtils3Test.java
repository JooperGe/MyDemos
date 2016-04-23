package com.jooper.mydemos;

import java.io.File;
import java.util.List;

import org.xutils.DbManager;
import org.xutils.DbManager.DaoConfig;
import org.xutils.DbManager.TableCreateListener;
import org.xutils.x;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.image.ImageOptions;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.jooper.mydemos.MyUtils.LogUtil;
import com.jooper.mydemos.base.BaseAct;
import com.jooper.mydemos.model.XUtilsEntity;
import com.jooper.mydemos.model.XUtilsEntityV5;

/**
 * xUtils3的使用
 * 
 * 1.在dbManager.save插入数据的时候会判断对应的表是否存在并根据情况新建表Table
 * 
 * @author Jooper
 *
 */
public class XUtils3Test extends BaseAct implements OnClickListener {

	private ImageView iv20_test;
	private TextView tv_latest_datas, tv_id2, tv_database_update;
	private Button btn20_test, btn20_db, btn20_db_add, btn20_db_delete,
			btn20_db_update, btn20_db_find, btn20_db_clear;

	private String imageUrl = "http://img5.duitang.com/uploads/item/201406/17/20140617140412_JKnZU.thumb.700_0.jpeg";
	private ImageOptions imageOptions;

	// private DbUtils dbUtils;

	private XUtilsEntity myEntity = null;
	private XUtilsEntityV5 myEntity5 = null;

	public static final String PACKAGE_NAME = "com.jooper.mydemos";

	// 默认数据库保存路径【可以不写】
	// public static final String DB_PATH = "/data"
	// + Environment.getDataDirectory().getAbsolutePath() + "/"
	// + PACKAGE_NAME + "/databases";
	// 自定义数据库保存路径
	public static final String DB_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/amap/mydb/";
	private int DB_VERSION = 8;// 数据库版本
	private DaoConfig daoConfig;
	private DbManager dbManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_xuitls);

		mContext = this;

		File file = new File(DB_PATH);

		daoConfig = new DbManager.DaoConfig().setDbName("XUtilsEntity")
				.setDbDir(file).setDbVersion(DB_VERSION)
				.setTableCreateListener(new TableCreateListener() {

					@Override
					public void onTableCreated(DbManager db,
							TableEntity<?> table) {

						// 成功建立了一张表
					}
				}).setDbUpgradeListener(new DbManager.DbUpgradeListener() {

					@Override
					public void onUpgrade(DbManager dbManager, int oldVersion,
							int newVersion) {
						// 升级数据库的时候做的操作
						// dbManager.addColumn(arg0, arg1);
						// dbManager.dropTable(arg0);

						try {

							dbManager.dropTable(XUtilsEntityV5.class);
							dbManager.addColumn(XUtilsEntity.class, "extra6");

						} catch (DbException e) {
							e.printStackTrace();
						}

						tv_database_update.setVisibility(View.VISIBLE);
						tv_database_update.setText("数据库 "
								+ daoConfig.getDbName() + " 升级到了 " + newVersion
								+ " 上一版本是 " + oldVersion);
					}
				});

		myEntity = new XUtilsEntity();
		myEntity.setAddTime(String.valueOf(System.currentTimeMillis()));
		myEntity.setAge(20);
		myEntity.setCity("Beijing");
		myEntity.setId(7);
		myEntity.setName("Test");

		myEntity5 = new XUtilsEntityV5();
		myEntity5
				.setAddTime("V5：" + String.valueOf(System.currentTimeMillis()));
		myEntity5.setAge(50);
		myEntity5.setCity("HeNan");
		myEntity5.setId(10);
		myEntity5.setName("TestV5");

		iv20_test = (ImageView) findViewById(R.id.iv20_test);
		tv_latest_datas = (TextView) findViewById(R.id.tv_latest_datas);
		tv_id2 = (TextView) findViewById(R.id.tv_id2);
		tv_database_update = (TextView) findViewById(R.id.tv_database_update);

		btn20_test = (Button) findViewById(R.id.btn20_test);
		btn20_test.setOnClickListener(this);
		btn20_db = (Button) findViewById(R.id.btn20_db);
		btn20_db.setOnClickListener(this);
		btn20_db_add = (Button) findViewById(R.id.btn20_db_add);
		btn20_db_add.setOnClickListener(this);
		btn20_db_delete = (Button) findViewById(R.id.btn20_db_delete);
		btn20_db_delete.setOnClickListener(this);
		btn20_db_update = (Button) findViewById(R.id.btn20_db_update);
		btn20_db_update.setOnClickListener(this);
		btn20_db_find = (Button) findViewById(R.id.btn20_db_find);
		btn20_db_find.setOnClickListener(this);
		btn20_db_clear = (Button) findViewById(R.id.btn20_db_clear);
		btn20_db_clear.setOnClickListener(this);

		imageOptions = new ImageOptions.Builder().setIgnoreGif(true)
				.setImageScaleType(ScaleType.FIT_XY)
				.setLoadingDrawableId(R.drawable.search_clear_pressed)
				.setRadius(50)// setRadius设置半径
				.setFailureDrawableId(R.drawable.ic_launcher).build();

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn20_test:

			try {

				x.image().bind(iv20_test, imageUrl, imageOptions,
						new CommonCallback<Drawable>() {

							@Override
							public void onCancelled(CancelledException arg0) {

								LogUtil.j("下载取消");
							}

							@Override
							public void onError(Throwable arg0, boolean arg1) {
								LogUtil.j("下载出错" + arg0.getMessage());
							}

							@Override
							public void onFinished() {
								LogUtil.j("下载完成");
							}

							@Override
							public void onSuccess(Drawable arg0) {
								LogUtil.j("下载成功");
							}
						});
			} catch (Exception e) {

				LogUtil.j("xuilts3:" + e.toString());
			}

			// 加载本地图片--也可以添加回调new CommonCallback<Drawable>()
			// x.image().bind(imgv, "assets://test.gif", options);
			// x.image().bind(iv_big_img, new
			// File("/sdcard/test.gif").toURI().toString(), imageOptions);
			// x.image().bind(iv_big_img, "/sdcard/test.gif", imageOptions);
			// x.image().bind(iv_big_img, "file:///sdcard/test.gif",
			// imageOptions);
			// x.image().bind(iv_big_img, "file:/sdcard/test.gif",
			// imageOptions);

			break;
		case R.id.btn20_db:

			// 创建数据库
			dbManager = x.getDb(daoConfig);

			// dbUtils = DbUtils.create(mContext, "XUtilsEntity");
			//
			// try {
			//
			// dbUtils.createTableIfNotExist(XUtilsEntity.class);
			//
			// Toast.makeText(mContext, "成功", 0).show();
			//
			// } catch (DbException e) {
			//
			// Toast.makeText(mContext, "失败", 0).show();
			// e.printStackTrace();
			// }

			break;
		case R.id.btn20_db_add:

			myEntity.setAddTime(String.valueOf(System.currentTimeMillis()));
			myEntity5.setAddTime("v5："
					+ String.valueOf(System.currentTimeMillis()));
			try {

				dbManager.save(myEntity);
				dbManager.save(myEntity5);

			} catch (DbException e) {

				e.printStackTrace();
			}

			showLatestDb();

			break;
		case R.id.btn20_db_delete:

			try {

				// dbManager.deleteById(XUtilsEntity.class, 3);
				dbManager.delete(XUtilsEntity.class,
						WhereBuilder.b("id", "==", 4));

				showLatestDb();

			} catch (DbException e) {

				e.printStackTrace();
			}

			break;
		case R.id.btn20_db_update:

			try {

				// 第一种更新
				// dbManager.update(XUtilsEntity.class,
				// WhereBuilder.b("id", "=", 7),
				// new KeyValue("name", "newName1"));
				// dbManager.update(XUtilsEntity.class,
				// WhereBuilder.b("id", "between", new int[] { 10, 13 }),
				// new KeyValue("name", "newName1"));

				/**
				 * 第二种更新
				 * 
				 * 更新name字段，将新的值mEntity的name字段的值赋给原有数据库中id为2的一条数据的name字段中
				 */
				XUtilsEntity mEntity = new XUtilsEntity();
				mEntity.setId(2);
				mEntity.setName("newTest");
				dbManager.update(mEntity, new String[] { "name" });

				showLatestDb();

			} catch (DbException e) {

				LogUtil.j(e.toString());
				e.printStackTrace();
			}

			break;
		case R.id.btn20_db_find:

			// xUtils3.0中使用Selector查询正确写法
			// mlistAppInfo_left = x_dbManager.selector(AppInfo_X3.class)
			// .where("addTo", "=", 1).orderBy("id").findAll();

			try {

				// 如果id的值不存在，则会NullPointerException
				XUtilsEntity mXUtilsEntity = dbManager.findById(
						XUtilsEntity.class, 2);

				tv_id2.setText(mXUtilsEntity.getId() + mXUtilsEntity.getName()
						+ mXUtilsEntity.getAddTime());

			} catch (DbException e) {

				e.printStackTrace();
			}

			break;
		case R.id.btn20_db_clear:

			try {

				// dbManager.delete(XUtilsEntity.class);// 删除entityType类型的所有数据

				// dbManager.deleteById(XUtilsEntity.class, 30);//
				// 删除id为30的这条记录；不存在不会NullPointerException

				// dbManager.dropTable(XUtilsEntity.class);//
				// 删除这张表(XUtilsEntity.class)；再次查询的时候如果没有这张表，则返回null

				dbManager.dropDb();// 删除掉当前数据库【中的所有表】

				showLatestDb();

			} catch (DbException e) {

				e.printStackTrace();
			}

			break;
		}
	}

	/**
	 * 打印数据库中最新数据
	 */
	private void showLatestDb() {

		List<XUtilsEntity> list = null;
		try {

			list = dbManager.findAll(XUtilsEntity.class);

		} catch (DbException e) {

			e.printStackTrace();
		}

		String latestStr = "";

		if (list != null) {

			for (int i = 0; i < list.size(); i++) {

				latestStr = latestStr + list.get(i).getId() + " - "
						+ list.get(i).getName() + " - "
						+ list.get(i).getAddTime() + "\n";
			}
		} else {

			latestStr = "dbManager.findAll为空";
		}

		tv_latest_datas.setText(latestStr);
	}

/**
	 *DbUtils db = DbUtils.create(this);
User user = new User(); //这里需要注意的是User对象必须有id属性，或者有通过@ID注解的属性
user.setEmail("wyouflf@qq.com");
user.setName("wyouflf");
db.save(user); // 使用saveBindingId保存实体时会为实体的id赋值
 
...
// 查找
Parent entity = db.findById(Parent.class, parent.getId());
List<Parent> list = db.findAll(Parent.class);//通过类型查找
 
Parent Parent = db.findFirst(Selector.from(Parent.class).where("name","=","test"));
 
// IS NULL
Parent Parent = db.findFirst(Selector.from(Parent.class).where("name","=", null));
// IS NOT NULL
Parent Parent = db.findFirst(Selector.from(Parent.class).where("name","!=", null));
 
// WHERE id<54 AND (age>20 OR age<30) ORDER BY id LIMIT pageSize OFFSET pageOffset
List<Parent> list = db.findAll(Selector.from(Parent.class)
                                   .where("id" ,"<", 54)
                                   .and(WhereBuilder.b("age", ">", 20).or("age", " < ", 30))
                                   .orderBy("id")
                                   .limit(pageSize)
                                   .offset(pageSize * pageIndex));
 
// op为"in"时，最后一个参数必须是数组或Iterable的实现类(例如List等)
Parent test = db.findFirst(Selector.from(Parent.class).where("id", "in", new int[]{1, 2, 3}));
// op为"between"时，最后一个参数必须是数组或Iterable的实现类(例如List等)
Parent test = db.findFirst(Selector.from(Parent.class).where("id", "between", new String[]{"1", "5"}));
 
DbModel dbModel = db.findDbModelAll(Selector.from(Parent.class).select("name"));//select("name")只取出name列
List<DbModel> dbModels = db.findDbModelAll(Selector.from(Parent.class).groupBy("name").select("name", "count(name)"));
...
 
List<DbModel> dbModels = db.findDbModelAll(sql); // 自定义sql查询
db.execNonQuery(sql) // 执行自定义sql

	 */
}
