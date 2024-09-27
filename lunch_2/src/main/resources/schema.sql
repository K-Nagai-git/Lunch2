-- テーブルが存在したら削除する
DROP TABLE IF EXISTS lunches;

-- テーブルの作成
CREATE TABLE lunches (
	-- id（管理ID）：主キー
    id serial PRIMARY KEY,
    -- menu（メニュー）：NULL不許可
    menu varchar(255) NOT NULL,
    -- shop（お店）
    shop text,
    -- price（値段）
    price integer, 
    -- recent_date（前回利用日）
    recent_date DATE,   
    -- times（回数）
    times integer,
    -- rate（評価）
    rate text,
    -- memo（メモ、コメント）
    memo text
);