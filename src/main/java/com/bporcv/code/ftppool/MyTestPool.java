//package com.bporcv.ftppool;
//
//import org.apache.commons.pool2.KeyedPooledObjectFactory;
//import org.apache.commons.pool2.PooledObject;
//import org.apache.commons.pool2.impl.DefaultPooledObject;
//import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
//
//public class MyTestPool {
//
//    private GenericKeyedObjectPool<String, FileUtilTest> myObjectPool;
//
//    public void init() {
//        if (this.myObjectPool == null) {
//            this.myObjectPool = new GenericKeyedObjectPool<String, FileUtilTest>(
//                    new KeyedPooledObjectFactory<String, FileUtilTest>() {
//
//                        public PooledObject<FileUtilTest> makeObject(String key)
//                                throws Exception {
//                            //这里是生成对象
//                            FileUtilTest file = new FileUtilTest();
//                            DefaultPooledObject<FileUtilTest> pooledObject = new DefaultPooledObject<FileUtilTest>(file);
//                            return pooledObject;
//                        }
//
//                        public void destroyObject(String key,
//                                                  PooledObject<FileUtilTest> p) throws Exception {
//                            System.out.println("an object closed");
//                        }
//
//                        public boolean validateObject(String key,
//                                                      PooledObject<FileUtilTest> p) {
//                            // TODO Auto-generated method stub
//                            //这里是返回是否对象池的对象是正确的，如果返回false的话 就会一直取对象，直到报错
//                            //这里需要对象中有一个判断方法来判断是否active
//                            //我就是瞎搞搞 直到这个方法是干嘛就行了
//                            return true;
//                        }
//
//                        //看borrowobject源代码，在成功从queue中获取到对象时，会调用这个方法来激活这个对象
//                        //然后再调用上面那个validateObject来确定对象是不是好用，所以说这个方法也很重要
//                        //if (null != factory && null != obj) { 
//                        //  try {
//                        //factory.activateObject(ref); 
//                        //    if (!factory.validateObject(ref)) { 
//                        //   throw new Exception("ValidateObject failed"); 
//                        //	 } 
//                        public void activateObject(String key,
//                                                   PooledObject<FileUtilTest> p) throws Exception {
//                            // TODO Auto-generated method stub
//
//                        }
//
//                        public void passivateObject(String key,
//                                                    PooledObject<FileUtilTest> p) throws Exception {
//                            // TODO Auto-generated method stub
//                        }
//                    }
//            );
//            // Set max idle (not max active) since our connections always idle in the pool.
//            this.myObjectPool.setMaxIdlePerKey(1);
//
//            // We always want our validate method to control when idle objects are evicted.
//            this.myObjectPool.setTestOnBorrow(true);
//            this.myObjectPool.setTestWhileIdle(true);
//        }
//    }
//
//    public GenericKeyedObjectPool<String, FileUtilTest> getPooded() {
//        return this.myObjectPool;
//    }
//
//    public synchronized FileUtilTest getFileUtilTest(String key) {
//
//        FileUtilTest test = null;
//
//        //这里我写死设置线程池中为5
//        if (this.getPooded().getNumIdle(key) < 5) {
//
//            try {
//                // we want borrowObject to return the one we added.
//                myObjectPool.setLifo(true);
//                myObjectPool.addObject(key);
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        } else {
//            myObjectPool.setLifo(false);
//        }
//        while (test == null) {
//            try {
//                //这里循环取出对象 有对象的话就break
//                //其实写的不好 可以看下人家的pool中的实现
//
//				/* while (connection == null) {
//                connection = connectionsPool.borrowObject(key);
//                synchronized (connection) {
//                    if (connection.getConnection() != null) {
//                        connection.incrementReferenceCount();
//                        break;
//                    }
//                    // Return the bad one to the pool and let if get destroyed as normal.
//                    connectionsPool.returnObject(key, connection);
//                    connection = null;
//                }
//            }*/
//                test = myObjectPool.borrowObject(key);
//                synchronized (test) {
//                    if (test != null) {
//                        break;
//                    }
//                    //这里如果失败了就把对象放回去
//                    myObjectPool.returnObject(key, test);
//                }
//            } catch (Exception e) {
//                System.out.println("this is message");
//                e.printStackTrace();
//            }
//        }
//        return test;
//    }
//
//    public static void main(String[] args) {
//
//        MyTestPool mypool = new MyTestPool();
//        mypool.init();
//        FileUtilTest file = mypool.getFileUtilTest("first");
//        FileUtilTest file2 = mypool.getFileUtilTest("second");
//        System.out.println(file);
//        System.out.println(file2);
//    }
//}
