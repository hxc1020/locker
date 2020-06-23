# Tasking

## locker
- GIVEN LOCKER有空间，包 WHEN 存包 THEN 存包成功，得到票
- GIVEN LOCKER没有空间 WHEN 存包 THEN 存包失败
- GIVEN 有效的票，WHEN 取包 THEN 取包成功
- GIVEN 取过包的有票 WHEN 取包 THEN 取包失败
- GIVEN 伪造票据 WHEN 取包 THEN 取包失败

## PrimaryLockerRobot
- GIVEN:机器人管理2个柜子, 都有剩余空间 WHEN: 存包 THEN: 包存到1号柜子，得到票
- GIVEN:机器人管理2个柜子，1号柜子满，2号柜子有剩余空间 WHEN: 存包 THEN: 包存到2号柜子，得到票
- GIVEN:机器人管理2个柜子，都无剩余 空间 WHEN: 存包 THEN: 存包失败
- GIVEN:机器人管理2个柜子，有效票 WHEN: 取包 THEN: 得到存的包
- GIVEN:机器人管理2个柜子，取过的票 WHEN: 取包 THEN: 取包失败
- GIVEN:机器人管理2个柜子，无效票 WHEN: 取包 THEN: 取包失败

## SmartLockerRobot
- given：SmartLockerRobot管理Locker1，Locker2，Locker1的剩余容量大于Locker2的剩余容量  
when：SmartLockerRobot存包  
then：返回ticket，成功存入Locker1  

- given：SmartLockerRobot管理Locker1，Locker2，Locker1的剩余容量小于Locker2的剩余容量  
when：SmartLockerRobot存包  
then：返回ticket，成功存入Locker2  

- given：SmartLockerRobot管理Locker1，Locker2，Locker1的剩余容量等于Locker2的剩余容量  
when：SmartLockerRobot存包  
then：返回ticket，成功存入Locker1  

- given：SmartLockerRobot管理Locker1，Locker2，Locker1和Locker2都存满了  
when：SmartLockerRobot存包  
then：提示已存满  

- given：SmartLockerRobot管理Locker1，Locker2，有一张有效的ticket  
when：SmartLockerRobot取包  
then：取到之前存入的包  

- given：SmartLockerRobot管理Locker1，Locker2，有一张无效的ticket  
when：SmartLockerRobot取包  
then：提示无效票  

- given：SmartLockerRobot管理Locker1，Locker2，PrimaryLockerRobot管理Locker2，Locker3，通过PrimaryLockerRobot存包到Locker2获取的票   
when：SmartLockerRobot取包  
then：取到之前存入的包  

- given：SmartLockerRobot管理Locker1，Locker2，PrimaryLockerRobot管理Locker2，Locker3，通过PrimaryLockerRobot存包到Locker3获取的票    
when：SmartLockerRobot取包  
then：提示无效票  

- given：SmartLockerRobot管理Locker1，Locker2，PrimaryLockerRobot管理Locker2，Locker3，通过SmartLockerRobot存包到Locker2获取的票    
when：PrimaryLockerRobot取包  
then：取到之前存入的包  

- given：SmartLockerRobot管理Locker1，Locker2，PrimaryLockerRobot管理Locker2，Locker3，通过SmartLockerRobot存包到Locker1获取的票  
when：PrimaryLockerRobot取包  
then：提示无效票  

## LockerRobotManager
- given：LockerRobotManager管理两个locker，没有管理robot，且两个locker都有剩余空间  
when：LockerRobotManager存包  
then：返回票据，成功存入第一个locker  

- given：LockerRobotManager管理两个locker，没有管理robot，locker1没有剩余空间，locker2有剩余空间  
when：LockerRobotManager存包  
then：返回票据，成功存入第二个locker  

- given：LockerRobotManager管理两个locker，没有管理robot，两个locker都没有剩余空间  
when：LockerRobotManager存包  
then：提示储物柜已满，存包失败  

- given：LockerRobotManager管理两个robot，没有管理locker，且两个robot管理的locker都有剩余空间  
when：LockerRobotManager存包  
then：返回票据，成功存入第一个robot管理的locker  

- given：LockerRobotManager管理两个robot，没有管理locker，第一个robot管理的locker没有剩余空间，第二个robot管理的locker有剩余空间  
when：LockerRobotManager存包  
then：返回票据，成功存入第二个robot管理的locker  

- given：LockerRobotManager管理两个robot，没有管理locker，且两个robot管理的locker都没有剩余空间  
when：LockerRobotManager存包  
then：提示储物柜已满，存包失败  

- given：LockerRobotManager管理locker1和一个robot，robot管理locker2，locker1和locker2都有剩余空间  
when：LockerRobotManager存包  
then：返回票据，成功存入locker2  

- given：LockerRobotManager管理locker1和一个robot，robot管理locker2，locker2已经存满  
when：LockerRobotManager存包  
then：返回票据，成功存入locker1  

- given：LockerRobotManager管理locker1和一个robot，robot管理locker2，locker1和locker都没有剩余空间  
when：LockerRobotManager存包  
then：提示储物柜已满，存包失败   

- given：LockerRobotManager管理两个robot，没有管理locker，票据是通过LockerRobotManage存包得到的  
when：LockerRobotManager取包  
then：取包成功  

- given：LockerRobotManager管理robot1、robot2，没有管理locker，票据是通过robot1存包得到的  
when：LockerRobotManager取包  
then：取包失败，提示票无效  

- given：LockerRobotManager管理locker1、locker2，没有管理robot，票据是通过LockerRobotManage存包得到的  
when：LockerRobotManager取包  
then：取包成功  

- given：LockerRobotManager管理locker1、locker2，没有管理robot，无效票据  
when：LockerRobotManager取包  
then：取包失败，提示票无效  

- given：LockerRobotManager管理一个locker1和一个robot，robot管理locker2，票据是通过LockerRobotManage存包得到的  
when：LockerRobotManager取包  
then：取包成功  

- given：LockerRobotManager管理一个locker1和一个robot，robot管理locker2，票据是通过robot存包得到的  
when：LockerRobotManager取包  
then：取包失败，提示票无效  
