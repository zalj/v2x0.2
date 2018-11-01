package com.qm.v2x.util;

public class posUtil {
	

	
    public static double getDistance(double lng1, double lat1, double lng2,double lat2) {
    	
		final double EARTH_RADIUS = 6378.137; //地球半径
		double radLat1 = Math.toRadians(lat1);
		double radLat2 = Math.toRadians(lat2);
		double a = radLat1 - radLat2;
		double b = Math.toRadians(lng1) - Math.toRadians(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
		+ Math.cos(radLat1) * Math.cos(radLat2)
		* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000d) / 10000d;
		s = s * 1000;
		return s;
		
    }
    
    public static double verticalDistance(double lng1, double lat1, double lng2,double lat2,double hvAngle) {
    	
    	double[] coord1=Geo.convertToXY(lng1, lat1);
    	double x1=coord1[0];
    	double y1=coord1[0];
    	double[] coord2=Geo.convertToXY(lng1, lat1);
    	double x2=coord2[0];
    	double y2=coord2[0];
		return Math.abs((x1-x2)*Math.sin(Math.toRadians(hvAngle))-(y1-y2)*Math.cos(Math.toRadians(hvAngle)));	
    	
    }
    
    public static boolean inFront(double lng1, double lat1, double lng2,double lat2,double hvAngle) {
    	
    	double[] coord1=Geo.convertToXY(lng1, lat1);
    	double x1=coord1[0];
    	double y1=coord1[0];
    	double[] coord2=Geo.convertToXY(lng1, lat1);
    	double x2=coord2[0];
    	double y2=coord2[0];
    	
    	double flag=(x2-x1)*Math.cos(Math.toRadians(hvAngle))+(y2-y1)*Math.sin(Math.toRadians(hvAngle));
    	if(flag>=0)
    		return true;
    	else
    		return false;
    }
    
    
    /***
     * 判断碰撞点是否在两车行驶前方
     *
     * @param svVector 主车到碰撞点向量
     * @param speAngle1 主车速度方向角
     * @param rvVector 远车到碰撞点向量
     * @param speAngle2 远车速度方向角
     * @return
     */
    public static boolean collisionPointIsRight(double[] svVector, double speAngle1, double[] rvVector, double speAngle2) {
        //正北基准向量
        double[] north = {0, 1};
        double angleSv = getRotateAngle(north[0], north[1], svVector[0], svVector[1]);
//        System.out.println(angleSv);
        double angleRv = getRotateAngle(north[0], north[1], rvVector[0], rvVector[1]);
//        System.out.println(angleRv);
        if (Math.abs(speAngle1 - angleSv) < 10 && Math.abs(speAngle2 - angleRv) < 10) {
            return true;
        } else {
            return false;
        }
    }
    
    /***
     * 计算两个向量顺时针夹角，
     *
     * @param x1 基准向量x坐标
     * @param y1 基准向量y坐标
     * @param x2 被测向量x坐标
     * @param y2 被测向量y坐标
     * @return
     */

    public static double getRotateAngle(double x1, double y1, double x2, double y2) {
        final double epsilon = 1.0e-9;
        final double nyPI = Math.acos(-1.0);
        double dist, dot, degree, angle;

        // normalize
        dist = Math.sqrt(x1 * x1 + y1 * y1);
        x1 /= dist;
        y1 /= dist;
        dist = Math.sqrt(x2 * x2 + y2 * y2);
        x2 /= dist;
        y2 /= dist;
        // dot product
        dot = x1 * x2 + y1 * y2;
        if (Math.abs(dot - 1.0) <= epsilon)
            angle = 0.0;
        else if (Math.abs(dot + 1.0) <= epsilon)
            angle = nyPI;
        else {
            double cross;

            angle = Math.acos(dot);
            //cross product
            cross = x1 * y2 - x2 * y1;
            // vector p2 is clockwise from vector p1
            // with respect to the origin (0.0)
            if (cross > 0) {
                angle = 2 * nyPI - angle;
            }
        }
        degree = angle * 180.0 / nyPI;
        return degree;
    }
    
    /***
     * 计算碰撞点坐标
     *
     * @param longitude1 主车经度
     * @param latitude1 主车纬度
     * @param speAngle1 主车速度方向角
     * @param longitude2 远车经度
     * @param latitude2 远车纬度
     * @param speAngle2 远车速度方向角
     * @return
     */

    public static double[] calculateCollisionPointXY(double longitude1, double latitude1, double speAngle1, double longitude2, double latitude2, double speAngle2) {
        //下标0代表碰撞点经度，1代表纬度
        double[] collisionPointXY = new double[2];
        //x,y分别代表经度和纬度
        double x1 = longitude1;
        double y1 = latitude1;
        double x2 = longitude2;
        double y2 = latitude2;
        if (speAngle1 != 90 && speAngle1 != 270 && speAngle2 != 90 && speAngle2 != 270) {
            double k1 = Math.tan(Math.toRadians(90 - speAngle1));
            double k2 = Math.tan(Math.toRadians(90 - speAngle2));
            collisionPointXY[0] = (-k2 * x2 + y2 + k1 * x1 - y1) / (k1 - k2);
            collisionPointXY[1] = k1 * (collisionPointXY[0] - x1) + y1;
            return collisionPointXY;
        }
        if (speAngle1 == 90 || speAngle1 == 270) {
            double k2 = Math.tan(Math.toRadians(90 - speAngle2));
            collisionPointXY[1] = y1;
            collisionPointXY[0] = y1 - y2 + k2 * x2;
            return collisionPointXY;
        }
        if (speAngle2 == 90 || speAngle2 == 270) {
            double k1 = Math.tan(Math.toRadians(90 - speAngle1));
            collisionPointXY[1] = y2;
            collisionPointXY[0] = y2 - y1 + k1 * x1;
            return collisionPointXY;
        }
        return null;
    }
    
    
}
