# project

## Build Project

- docker 설치
  - ``` 
    $ sudo apt-get install docker-ce docker-ce-cli containerd.io docker-compose-plugin

### docker-compose로 mysql, admin, user 서버 실행방법
- 프로젝트 최상단 디렉토리에서 docker_compose_start.sh를 실행
  - ```
    ./docker_compose_start.sh
    ```
  - 한번에 mysql, admin, user 서버가 실행

### port 정보
  - mysql-port: 3306
  - module_admin-port: 18080
  - module_user-port: 18081

## 관리 기능 (admin)
#### 카테고리 등록
- [POST] http://localhost:18080/category
- body
  - ```
    {
      "parentId": null,
      "name": "카테고리1",
      "url": "/category1"    
    }
  - ```
#### 카테고리 수정
- [PUT] http://localhost:18080/category
- body
  - ```
    {
      "categoryId": 2,
      "name": "카테고리2",
      "url": "/category2-1"    
    }
    ```
#### 카테고리 삭제
  - [DELETE] http://localhost:18080/category/{id}
#### 카테고리 조회
- [GET] http://localhost:18080/category/{id}
- response
  - ```
    {
      "id": 3,
      "name": "카테고리3",
      "url": "/category3",
      "isActive": true,
      "createdAt": "2023-04-08T05:12:40",
      "updatedAt": "2023-04-08T05:12:40",
      "bannerList": []
    }
    ```
#### 카테고리 depth별 목록
- [GET] http://localhost:18080/category/list/{depth}
- response
  - ```
    [
      {
        "id": 1,
        "name": "카테고리1",
        "url": "/category1",
        "isActive": true,
        "createdAt": "2023-04-08T05:12:30",
        "updatedAt": "2023-04-08T05:12:30",
        "bannerList": []
      }, ...
    ]
    ```
#### 카테고리의 하위 카테고리 조회
- [GET] http://localhost:18080/category/{id}/subcategory/list
- response
  - ```
    {
      "categoryId": 3,
      "name": "카테고리3",
      "url": "/category3",
      "isActive": true,
      "createdAt": "2023-04-08T05:12:40",
      "updatedAt": "2023-04-08T05:12:40",
      "children": [
          {
              "categoryId": 4,
              "name": "카테고리3-1",
              "url": "/category3/sub-1",
              "isActive": true,
              "createdAt": "2023-04-08T05:19:57",
              "updatedAt": "2023-04-08T05:19:57",
              "children": []
          }
      ]
    }
    ```
#### 카테고리 순서 & 상위 카테고리 변경
- [PUT] http://localhost:18080/category/sort
- body
  - ```
    {
      "sortedList" : [
        {
          "categoryId" : 23,
          "parentId" : 10,
          "sort" : 1,
          "depth" : 2
        }, ...
      ]
    }
    ```
#### 카테고리 배너 등록
- [POST] http://localhost:18080/category/banner
- body
  - ```
    {
      "categoryId" : 2,
      "attachmentId" : 1,
      "bannerUrl" : "/test",
      "memo" : "룰루랄라",
      "isActive" : true
    }
    ```
#### 카테고리 배너 수정
- [PUT] http://localhost:18080/category/banner
- body
  - ```
    {
      "categoryBannerId" : 2,
      "attachmentId" : 1,
      "bannerUrl" : "/test",
      "memo" : "룰루랄라",
      "isActive" : true
    }
    ```
#### 카테고리 배너 삭제
- [DELETE] http://localhost:18080/category/banner/{id}
#### 카테고리 배너 조회
- [GET] http://localhost:18080/category/{id}
- response
  - ```
    {
      "id": 1,
      "attachment": {
        "fileType": "IMAGE",
        "fileOrgName": "로그인.png",
        "fileName": "a4bb1810-e68a-4c3e-8631-0df9eb90070f",
        "filePath": "/a4bb1810-e68a-4c3e-8631-0df9eb90070f",
        "fileExtension": "image/png",
        "width": 1281,
        "height": 1033
      },
      "bannerUrl": "/test",
      "memo": "룰루랄라",
      "isActive": true,
      "createdAt": "2023-04-08T05:35:35",
      "updatedAt": "2023-04-08T05:35:35"
    }
    ```
#### 카테고리 배너 순서 변경
- [POST] http://localhost:18080/category/banner/sort
- body
  - ```
    {
      "sortedList" : [
        {
          "categoryBannerId" : 2,
          "sort" : 3
        }, ...
      ]
    }
    ```
#### 첨부파일 업로드
- [POST] http://localhost:18080/attachment/upload
- form
  - file: 파일

## 사용자 기능 (user)
#### 카테고리 조회
- [GET] http://localhost:18081/category/{id}
- response
  - ```
    {
      "id": 3,
      "name": "카테고리3",
      "url": "/category3",
      "isActive": true,
      "createdAt": "2023-04-08T05:12:40",
      "updatedAt": "2023-04-08T05:12:40",
      "bannerList": []
    }
    ```
#### 카테고리 depth별 목록
- [GET] http://localhost:18081/category/list/{depth}
- response
  - ```
    [
      {
        "id": 1,
        "name": "카테고리1",
        "url": "/category1",
        "isActive": true,
        "createdAt": "2023-04-08T05:12:30",
        "updatedAt": "2023-04-08T05:12:30",
        "bannerList": []
      }, ...
    ]
    ```
#### 카테고리의 하위 카테고리 조회
- [GET] http://localhost:18081/category/{id}/subcategory/list
- response
  - ```
    {
      "categoryId": 3,
      "name": "카테고리3",
      "url": "/category3",
      "isActive": true,
      "createdAt": "2023-04-08T05:12:40",
      "updatedAt": "2023-04-08T05:12:40",
      "children": [
          {
              "categoryId": 4,
              "name": "카테고리3-1",
              "url": "/category3/sub-1",
              "isActive": true,
              "createdAt": "2023-04-08T05:19:57",
              "updatedAt": "2023-04-08T05:19:57",
              "children": []
          }
      ]
    }
    ```