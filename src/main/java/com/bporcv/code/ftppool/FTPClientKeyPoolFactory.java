//package com.bporcv.ftppool;
//
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
//import org.apache.commons.pool2.PooledObject;
//import org.apache.commons.pool2.impl.DefaultPooledObject;
//import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
//import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
//
//public class FTPClientKeyPoolFactory {
//    /**
//     * 对象池
//     */
//    private static GenericKeyedObjectPool<String, FTPClient> pool;
//    /**
//     * 对象池的参数设置
//     */
//    private static final GenericKeyedObjectPoolConfig config;
//
//    /**
//     * 对象池每个key最大实例化对象数
//     */
//    private final static int TOTAL_PERKEY = 10;
//    /**
//     * 对象池每个key最大的闲置对象数
//     */
//    private final static int IDLE_PERKEY = 3;
//
//    static {
//        config = new GenericKeyedObjectPoolConfig();
//        config.setMaxTotalPerKey(TOTAL_PERKEY);
//        config.setMaxIdlePerKey(IDLE_PERKEY);
//        /** 支持jmx管理扩展 */
//        config.setJmxEnabled(true);
//        config.setJmxNamePrefix("myPoolProtocol");
//        /** 保证获取有效的池对象 */
//        config.setTestOnBorrow(true);
//        config.setTestOnReturn(true);
//    }
//
//    /**
//     * 从对象池中获取对象
//     *
//     * @param key
//     * @return
//     * @throws Exception
//     */
//    public static FTPClient getBean(String key) throws Exception {
//        if (pool == null) {
//            init();
//        }
//        return pool.borrowObject(key);
//    }
//
//    /**
//     * 归还对象
//     *
//     * @param key
//     * @param bean
//     */
//    public static void returnBean(String key, FTPClient bean) {
//        if (pool == null) {
//            init();
//        }
//        pool.returnObject(key, bean);
//    }
//
//    /**
//     * 关闭对象池
//     */
//    public synchronized static void close() {
//        if (pool != null && !pool.isClosed()) {
//            pool.close();
//            pool = null;
//        }
//    }
//
//    /**
//     * 初始化对象池
//     */
//    private synchronized static void init() {
//        if (pool != null)
//            return;
//        pool = new GenericKeyedObjectPool<String, FTPClient>(new MyBeanPooledFactory(), config);
//    }
//
//    /**
//     * 对象工厂
//     */
//    static class MyBeanPooledFactory extends BaseKeyedPooledObjectFactory<String, FTPClient> {
//        /**
//         * 创建对象
//         *
//         * @param key
//         * @return
//         * @throws Exception
//         */
//        public FTPClient create(String key) throws Exception {
//            FTPClient myBean = new FTPClient();
////            myBean.start();
//            System.out.println("create bean :" + myBean);
//            return myBean;
//        }
//
//        public PooledObject<FTPClient> wrap(FTPClient value) {
//            return new DefaultPooledObject<FTPClient>(value);
//        }
//
//        /**
//         * 验证对象是否有效
//         *
//         * @param key
//         * @param p
//         * @return
//         */
//        public boolean validateObject(String key, PooledObject<FTPClient> p) {
//            FTPClient bean = p.getObject();
////            if (!bean.isLive()) {
////                System.out.println(bean.getName() + "已经死了，无法唤醒他了!");
////                return false;
////            }
//            return true;
//        }
//
//        /**
//         * 销毁
//         *
//         * @param key
//         * @param p
//         * @throws Exception
//         */
//        public void destroyObject(String key, PooledObject<FTPClient> p) throws Exception {
//            /** 杀死他 */
////            p.getObject().beKilled();
//        }
//
//        public void activateObject(String key, PooledObject<FTPClient> p) throws Exception {
//            super.activateObject(key, p);
//        }
//
//        public void passivateObject(String key, PooledObject<FTPClient> p) throws Exception {
//            super.passivateObject(key, p);
//        }
//    }
//
//}
