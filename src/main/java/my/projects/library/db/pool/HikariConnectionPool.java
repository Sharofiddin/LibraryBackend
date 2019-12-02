package my.projects.library.db.pool;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import com.zaxxer.hikari.HikariDataSource;

public class HikariConnectionPool extends UnpooledDataSourceFactory {
	public HikariConnectionPool() {
		this.dataSource = new HikariDataSource();
	}
}
