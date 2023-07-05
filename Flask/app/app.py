import json
import cx_Oracle

from flask import Flask  # 서버 구현을 위한 Flask 객체 import
from apscheduler.schedulers.background import BackgroundScheduler  # 스케줄 사용
from flask_restx import Api, Resource  # Api 구현을 위한 Api 객체 import

app = Flask(__name__)  # Flask 객체 선언, 파라미터로 어플리케이션 패키지의 이름을 넣어줌.
api = Api(app)  # Flask 객체에 Api 객체 등록

cx_Oracle.init_oracle_client(lib_dir=r"/Users/pkd/Oracle/instantclient_19_8")
connection = cx_Oracle.connect(user='DA23', password='DA', dsn='dinkdb_high')


# 스케줄 실행 코드
def scheduler():
    # pykrx 이용 DB 갱신 구현
    print("Scheduler is alive!")


# 스케줄 작업 설정 (한국 시간 기준 16시 스케쥴 메서드 실행)
schedule = BackgroundScheduler(daemon=True, timezone='Asia/Seoul')
schedule.add_job(scheduler, 'cron', hour=16)
schedule.start()
# schedule = BackgroundScheduler(daemon=True, timezone='Asia/Seoul')
# schedule.add_job(scheduler, 'interval', seconds=3)
# schedule.start()

# ---------------------------------------------------------------------------------------------------------------------#
# Restful API : 데코레이터 이용. 해당 경로에 클래스 등록
@api.route("/stock_info/<string:stockName>")
class StockInfo(Resource):
    def get(self, stockName):  # GET 요청시 리턴 값에 해당 하는 dict를 JSON 형태로 반환
        # 커서 생성
        cursor = connection.cursor()

        # SELECT 쿼리 실행
        query = "SELECT * FROM emp"
        cursor.execute(query)

        # 결과 가져오기
        for row in cursor:
            column1_value = row[0]
            column2_value = row[1]
            print("Column1:", column1_value)
            print("Column2:", column2_value)

        # 커서, connection 종료
        cursor.close()
        connection.close()

        data = {"주식명": stockName}

        # 한글 설정
        response = app.response_class(
            response=json.dumps(data, ensure_ascii=False),
            status=200,
            mimetype="application/json"
        )

        return response


if __name__ == "__main__":
    app.debug = True
    app.run(debug=True, host='0.0.0.0', port=80)
