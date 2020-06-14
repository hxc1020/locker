# Tasking

## locker
- GIVEN LOCKER有空间，包 WHEN 存包 THEN 存包成功，得到票
- GIVEN LOCKER没有空间 WHEN 存包 THEN 存包失败
- GIVEN 有效的票，WHEN 取包 THEN 取包成功
- GIVEN 取过包的有票 WHEN 取包 THEN 取包失败
- GIVEN 伪造票据 WHEN 取包 THEN 取包失败

## robot
- GIVEN:机器人管理2个柜子, 都有剩余空间 WHEN: 存包 THEN: 包存到1号柜子，得到票
- GIVEN:机器人管理2个柜子，1号柜子满，2号柜子有剩余空间 WHEN: 存包 THEN: 包存到2号柜子，得到票
- GIVEN:机器人管理2个柜子，都无剩余 空间 WHEN: 存包 THEN: 存包失败
- GIVEN:机器人管理2个柜子，有效票 WHEN: 取包 THEN: 得到存的包
- GIVEN:机器人管理2个柜子，取过的票 WHEN: 取包 THEN: 取包失败
- GIVEN:机器人管理2个柜子，无效票 WHEN: 取包 THEN: 取包失败