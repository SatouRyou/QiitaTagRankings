# coding: utf-8
import json
import requests

# エンドポイント
endpoint = 'http://qiita.com/api/v2/tags'
for p in range(1, 2): # 1ページから10ページまで同様に以下の処理を行う
    payload = {'page': p, 'per_page': '100'} #1ページあたり100個のデータを入手
    r =  requests.get(endpoint, params=payload).json() #結果をjson形式で受け取る
    print r[0]
