local payload = {}

local RES_FLAG = "result";

--获取传入的键值
local key = KEYS[1]

--获取时间限制
local limitTime = tonumber(ARGV[1])

--获取次数限制
local maxCount = tonumber(ARGV[2])

--让value自增1,如果不存在会自动创建
redis.call('INCRBY', key, 1)

--获取当前次数
local currentCount = tonumber(redis.call('GET', key))

--大于限制次数则返回-1
if currentCount > maxCount then
    payload[RES_FLAG] = -1
    --等于1说明是第一次访问,则设置key的过期时间
elseif currentCount == 1 then
    payload[RES_FLAG] = currentCount
    redis.call("EXPIRE", key, limitTime)
else
    payload[RES_FLAG] = currentCount
end

return cjson.encode(payload)
