<template>
<div>
  <div >
    <el-row >
      <el-col :span="12">
        <el-input style="width: 400px" suffix-icon="el-icon-search" v-model="name"></el-input>
        <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
      </el-col>
      <el-col :span="2" :offset="8">
        <el-upload action="http://localhost:9090/file/upload" :show-file-list="false" :on-success="handleFileUpLoadSuccess">
          <el-button type="success" >上传文件</el-button>
        </el-upload>
      </el-col>
      <el-col :span="2">
        <el-popconfirm
            confirm-button-text="确定"
            cancel-button-text="我再想想"
            title="你确定？"
            @confirm ="delBatch">
          <el-button type="danger" slot="reference">批量删除</el-button>
        </el-popconfirm>
      </el-col>
    </el-row>
  </div>

  <el-table :data="tableData" @selection-change="handleSelectionChange">
    <el-table-column
        type="selection"
        width="55">
    </el-table-column>
    <el-table-column prop="id" label="ID" width="80">
    </el-table-column>
    <el-table-column prop="name" label="文件名" width="140">
    </el-table-column>
    <el-table-column prop="type" label="文件类型" width="120">
    </el-table-column>
    <el-table-column prop="size" label="文件大小KB">
    </el-table-column>
    <el-table-column label="下载" >
      <template slot-scope="scope">
        <el-button type="primary" @click="download(scope.row.url)">下载</el-button>
      </template>
    </el-table-column>
    <el-table-column label="启用" >
      <template slot-scope="scope">
        <el-switch v-model="scope.row.enable" active-color="#13ce66" inactive-color="#ccc" @change="changeEnable(scope.row)"></el-switch>
      </template>
    </el-table-column>

    <el-table-column label="操作" >
      <template slot-scope="scope">
        <el-popconfirm
            confirm-button-text="确定"
            cancel-button-text="我再想想"
            title="你确定？"
            @confirm ="del(scope.row.id)">
          <el-button type="danger" slot="reference">删除</el-button>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <div style="padding: 10px 0">
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[2, 5,10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
  </div>

</div>
</template>

<script>
export default {
  name: "File",
  data() {
    return {
      tableData: [],
      name: "",
      multipleSelection: [],
      pageNum: 1,
      pageSize: 10,
      total: 0
    }
  },
  mounted() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/file/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },

    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },

    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    del(id) {
      this.request.delete("/file/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)
      this.request.post("/file/del/batch", ids).then(res => {
        if (res.code === '200') {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    handleFileUpLoadSuccess(res) {
      console.log(res)
      this.load()
    },
    download(url) {
      window.open(url)
    },
    changeEnable(row) {
      this.request.post("/file/update", row).then(res => {
        if (res.code === '200') {
          this.$message.success("操作成功")
        }
      })
    }
  }
}
</script>

<style scoped>

</style>