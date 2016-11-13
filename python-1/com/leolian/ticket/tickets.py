# coding:utf-8
'''
Created on 2016年11月13日

@author: Lian
'''
from docopt import docopt


'''命令行火车票查看器
Usage:
    tickets [-gdtkz] <from> <to> <date>

Options:
    -h,--help   显示帮助菜单
    -g          高铁
    -d          动车
    -t          特快
    -k          快速
    -z          直达

Example:
    tickets 北京 上海 2016-10-10
    tickets -dg 成都 南京 2016-10-10
'''

def cli():
    """命令行的接口"""
    arguments = docopt(__doc__)
    print(arguments)


if __name__ == '__main__':
    cli()


