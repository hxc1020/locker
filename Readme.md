Tasking
GIVEN LOCKER有空间，包 WHEN 存包 THEN 存包成功，得到票
GIVEN LOCKER没有空间 WHEN 存包 THEN 存包失败
GIVEN 有效的票，WHEN 取包 THEN 取包成功
GIVEN 取过包的有票 WHEN 取包 THEN 取包失败
GIVEN 伪造票据 WHEN 取包 THEN 取包失败